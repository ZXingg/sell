package com.zxing.sell.controller;

import com.zxing.sell.VO.ResultVO;
import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.enums.ResultEnum;
import com.zxing.sell.exception.SellException;
import com.zxing.sell.form.OrderForm;
import com.zxing.sell.service.imp.OrderServiceImp;
import com.zxing.sell.utils.OrderForm2OrderMasterDTOConverter;
import com.zxing.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ZXing at 2018/2/13
 * QQ:1490570560
 */
@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class OrderController {

    @Autowired
    private OrderServiceImp orderServiceImp;

    @PostMapping("/create")
    public ResultVO<Map<String, String>> create(@Valid OrderForm orderForm, BindingResult bindingResult) {
        //检查参数
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】，错误。orderForm:{}", orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode()
                    , bindingResult.getFieldError().getDefaultMessage());
        }
        //转换
        OrderMasterDTO orderMasterDTO = OrderForm2OrderMasterDTOConverter.conver(orderForm);
        if (CollectionUtils.isEmpty(orderMasterDTO.getOrderDetailList())) {
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        //创建
        OrderMasterDTO createResult = orderServiceImp.create(orderMasterDTO);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    @GetMapping("/list")
    public ResultVO<List<OrderMasterDTO>> list(@RequestParam("openid") String openid,
                                               @RequestParam(value = "page", defaultValue = "0") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size) {
        if (StringUtils.isEmpty(openid)){
            log.error("【查询订单列表】openid不能为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        List<OrderMasterDTO> orderMasterDTOS=orderServiceImp.findList(openid,new PageRequest(page,size)).getContent();

        return ResultVOUtil.success(orderMasterDTOS);
    }

    @GetMapping("/detail")
    public ResultVO<OrderMasterDTO> detail(@RequestParam("openid") String openid,
                                                 @RequestParam("orderId") String orderId){
        //TODO  检查openid 是否为本人
        OrderMasterDTO orderMasterDTO=orderServiceImp.findOne(orderId);

        return ResultVOUtil.success(orderMasterDTO);
    }

    @PostMapping("/cancle")
    public ResultVO cancle(@RequestParam("openid") String openid,
                             @RequestParam("orderId") String orderId){
        //TODO  检查openid 是否为本人
        OrderMasterDTO orderMasterDTO=orderServiceImp.findOne(orderId);
        orderServiceImp.cancle(orderMasterDTO);

        return ResultVOUtil.success();
    }
}
