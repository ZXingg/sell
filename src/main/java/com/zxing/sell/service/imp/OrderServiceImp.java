package com.zxing.sell.service.imp;

import com.zxing.sell.controller.WebSocket;
import com.zxing.sell.dto.CartDTO;
import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.enums.OrderStatusEnum;
import com.zxing.sell.enums.PayStatusEnum;
import com.zxing.sell.enums.ResultEnum;
import com.zxing.sell.exception.SellException;
import com.zxing.sell.model.OrderDetail;
import com.zxing.sell.model.OrderMaster;
import com.zxing.sell.model.ProductInfo;
import com.zxing.sell.repository.OrderDetailRepository;
import com.zxing.sell.repository.OrderMasterRepository;
import com.zxing.sell.service.OrderService;
import com.zxing.sell.utils.OrderMaster2OrderMasterDTOConverter;
import com.zxing.sell.utils.keyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ZXing at 2018/2/12
 * QQ:1490570560
 */
@Slf4j
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private ProductInfoServiceImp productInfoServiceImp;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private OrderMasterRepository orderMasterRepository;
    @Autowired
    private PayServiceImp payServiceImp;
    @Autowired
    private PushMessageServiceImp pushMessageServiceImp;
    @Autowired
    private WebSocket webSocket;

    @Override
    @Transactional
    public OrderMasterDTO create(OrderMasterDTO orderMasterDTO) {
        String orderId = keyUtil.createUniqueKey();
        BigDecimal orderAmout = new BigDecimal(0);
        List<OrderDetail> saveOrderDetails = new ArrayList<>();
        //查询商品（数量，库存，单价）
        for (OrderDetail orderDetail : orderMasterDTO.getOrderDetailList()) {
            ProductInfo productInfo = productInfoServiceImp.findOne(orderDetail.getProductId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
                // throw new ResponseBankException();
            }
            //计算总价
            orderAmout = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmout);

            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetail.setDetailId(keyUtil.createUniqueKey());
            orderDetail.setOrderId(orderId);
            saveOrderDetails.add(orderDetail);
        }
        //写入订单主表
        OrderMaster orderMaster = new OrderMaster();
        orderMasterDTO.setOrderId(orderId);
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        orderMaster.setOrderAmount(orderAmout);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //写入订单详情  外键约束 需在主表存入后
        orderDetailRepository.save(saveOrderDetails);
        //扣库存
        List<CartDTO> cartDTOS = orderMasterDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoServiceImp.decreaseStock(cartDTOS);

        //发送消息至前端
        webSocket.sendMsg("你有新的订单，订单id = " + orderMasterDTO.getOrderId());

        return orderMasterDTO;
    }

    @Override
    public OrderMasterDTO findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findOne(orderId);
        if (orderMaster == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(orderId);
        if (CollectionUtils.isEmpty(orderDetails)) {
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderMasterDTO orderMasterDTO = new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster, orderMasterDTO);
        orderMasterDTO.setOrderDetailList(orderDetails);

        return orderMasterDTO;
    }

    @Override
    public Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid, pageable);
        List<OrderMasterDTO> orderMasterDTOS = OrderMaster2OrderMasterDTOConverter.converList(orderMasterPage.getContent());
        return new PageImpl(orderMasterDTOS, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    public Page<OrderMasterDTO> findList(Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findAll(pageable);
        List<OrderMasterDTO> orderMasterDTOS = OrderMaster2OrderMasterDTOConverter.converList(orderMasterPage.getContent());
        return new PageImpl(orderMasterDTOS, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderMasterDTO cancle(OrderMasterDTO orderMasterDTO) {
        //查询订单状态
        if (!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【取消订单】订单状态不正确，orderId:{},orderStatus;{}", orderMasterDTO.getOrderId(), orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【取消订单】更新失败! orderMaster:{}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        if (orderMasterDTO.getOrderDetailList().isEmpty()) {
            log.error("【取消订单】订单中无订单详情，orderDTO", orderMasterDTO);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        //返还库存
        List<CartDTO> cartDTOS = orderMasterDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoServiceImp.increaseStock(cartDTOS);

        //退款
        if (orderMasterDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            payServiceImp.refund(orderMasterDTO);
        }
        return orderMasterDTO;
    }

    @Override
    @Transactional
    public OrderMasterDTO finish(OrderMasterDTO orderMasterDTO) {
        //查询订单状态
        if (!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【完结订单】订单状态不正确，orderId:{},orderStatus;{}", orderMasterDTO.getOrderId(), orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDTO.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【完结订单】更新失败! orderMaster:{}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }

        //推送模板消息
        pushMessageServiceImp.orderStatusUpd(orderMasterDTO);

        return orderMasterDTO;
    }

    @Override
    @Transactional
    public OrderMasterDTO paid(OrderMasterDTO orderMasterDTO) {
        //查询订单状态
        if (!orderMasterDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
            log.error("【支付订单】订单状态不正确，orderId:{},orderStatus;{}", orderMasterDTO.getOrderId(), orderMasterDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //查询支付状态
        if (!orderMasterDTO.getPayStatus().equals(PayStatusEnum.WAIT.getCode())) {
            log.error("【支付订单】订单支付状态不正确，orderId:{},payStatus;{}", orderMasterDTO.getOrderId(), orderMasterDTO.getPayStatus());
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderMasterDTO.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderMasterDTO, orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null) {
            log.error("【支付订单】更新失败! orderMaster:{}", orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderMasterDTO;
    }


}
