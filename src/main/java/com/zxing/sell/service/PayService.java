package com.zxing.sell.service;

import com.github.binarywang.wxpay.exception.WxPayException;
import com.zxing.sell.dto.OrderMasterDTO;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZXing at 2018/2/14
 * QQ:1490570560
 */
public interface PayService {
    String create(OrderMasterDTO orderMasterDTO) throws WxPayException;

    String notify(HttpServletRequest request);

    String refund(OrderMasterDTO orderMasterDTO);
}