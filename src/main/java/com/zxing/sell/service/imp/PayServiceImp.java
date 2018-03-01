package com.zxing.sell.service.imp;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayBaseResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.enums.ResultEnum;
import com.zxing.sell.exception.SellException;
import com.zxing.sell.service.PayService;
import com.zxing.sell.utils.DateUtil;
import com.zxing.sell.utils.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Date;

/**
 * Created by ZXing at 2018/2/14
 * QQ:1490570560
 */
@Service
@Slf4j
public class PayServiceImp implements PayService {
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private OrderServiceImp orderServiceImp;

    @Override
    //TODO 返回类型？
    public String create(OrderMasterDTO orderMasterDTO) throws WxPayException {
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody("微信点餐订单");
        orderRequest.setOutTradeNo(orderMasterDTO.getOrderId());
        orderRequest.setTotalFee(WxPayBaseRequest.yuanToFee(String.valueOf(orderMasterDTO.getOrderAmount())));//元转成分
        orderRequest.setOpenid(orderMasterDTO.getBuyerOpenid());
        //orderRequest.setSpbillCreateIp(request.getRemoteHost());
        Date start = new Date();
        Date expire = new Date(start.getTime() + 15 * 60 * 1000);
        orderRequest.setTimeStart(DateUtil.format(start));
        orderRequest.setTimeExpire(DateUtil.format(expire));
        log.info("【微信支付】orderRequest={}", JsonUtil.toJson(orderRequest));

        return (String) wxPayService.createOrder(orderRequest);
    }

    @Override
    public String notify(HttpServletRequest request) {
        WxPayOrderNotifyResult result;
        try {
            String xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
            result = wxPayService.parseOrderNotifyResult(xmlResult);
            log.info("【微信支付】异步通知，payResponse={}", result);
        } catch (IOException | WxPayException e) {
            log.error("微信回调结果异常,异常原因{}", e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }
        // 结果正确
        String orderId = result.getOutTradeNo();
        String tradeNo = result.getTransactionId();
        String totalYuan = WxPayBaseResult.feeToYuan(result.getTotalFee());
        //自己处理订单的业务逻辑，需要判断订单是否已经支付过，否则可能会重复调用
        //验证签名
        //支付状态
        //支付金额
        //支付人（支付人==下单人） 或可代付
        OrderMasterDTO orderMasterDTO = orderServiceImp.findOne(orderId);
        if (orderMasterDTO == null) {
            log.error("【微信支付】异步通知，订单不存在,orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //验证金额  --double使用减法 if(v1-v2<0.01) 则ture
        if (!totalYuan.equals(String.valueOf(orderMasterDTO.getOrderAmount()))) {
            log.error("【微信支付】异步通知，订单金额不一致，orderId={},通知金额={},系统金额={}"
                    , orderId, totalYuan, orderMasterDTO.getOrderAmount());
            throw new SellException(ResultEnum.WECHAT_PAY_NOTIFY_MONEY_VERIFY_ERROR);

        }
        //修改支付状态
        orderServiceImp.paid(orderMasterDTO);

        return WxPayNotifyResponse.success("处理成功!");


    }

    @Override
    public String refund(OrderMasterDTO orderMasterDTO) {
        //TODO  https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
        return null;
    }

}
