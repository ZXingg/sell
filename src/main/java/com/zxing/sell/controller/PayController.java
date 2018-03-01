package com.zxing.sell.controller;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.request.WxPayBaseRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.zxing.sell.VO.ResultVO;
import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.enums.ResultEnum;
import com.zxing.sell.exception.SellException;
import com.zxing.sell.service.OrderService;
import com.zxing.sell.service.imp.OrderServiceImp;
import com.zxing.sell.service.imp.PayServiceImp;
import com.zxing.sell.utils.DateUtil;
import com.zxing.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created by ZXing at 2018/2/14
 * QQ:1490570560
 */
@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderServiceImp orderServiceImp;

    @Autowired
    private PayServiceImp payServiceImp;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String, String> map
    ) {//,HttpServletRequest request
        OrderMasterDTO orderMasterDTO = orderServiceImp.findOne(orderId);
        if (orderMasterDTO == null) {
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        //发起支付
        String payResponse = null;
        try {
            payResponse = payServiceImp.create(orderMasterDTO);
        } catch (WxPayException e) {
            log.error("【微信支付】发送错误，订单号={},错误原因={}", orderMasterDTO.getOrderId(), e.getReturnMsg());
            throw new SellException(ResultEnum.WECHAT_PAY_ERROR);
        }
        map.put("payResponse", payResponse);

        return new ModelAndView(returnUrl, map);//ResultVOUtil.success(orderMasterDTO);
    }

    @PostMapping("/notify")
    public String notify(HttpServletRequest request) {
        //返回给微信后台
        return payServiceImp.notify(request);
    }



}
