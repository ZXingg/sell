package com.zxing.sell.config;

import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Created by ZXing at 2018/2/14
 * QQ:1490570560
 */
@Component
public class WechatPayConfig {
    @Autowired
    private WechatAccountProperties properties;

    @Bean
    //@ConditionalOnMissingBean
    public WxPayConfig payConfig() {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(this.properties.getMpAppid());
        payConfig.setMchId(this.properties.getMchId());
        payConfig.setMchKey(this.properties.getMchKey());
        //payConfig.setSubAppId(StringUtils.trimToNull(this.properties.getSubAppId()));
        //payConfig.setSubMchId(StringUtils.trimToNull(this.properties.getSubMchId()));
        payConfig.setKeyPath(this.properties.getKeyPath());

        return payConfig;
    }

    @Bean
    //@ConditionalOnMissingBean
    public WxPayService wxPayService() {
        WxPayService wxPayService = new WxPayServiceImpl();
        wxPayService.setConfig(payConfig());
        return wxPayService;
    }
}
