package com.zxing.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**\
 * 开放平台配置
 * Created by ZXing at 2018/2/13
 * QQ:1490570560
 */
@Component
public class WechatOpenConfig {
    @Autowired
    private WechatAccountProperties wechatAccountProperties;

    @Bean
    public WxMpService wxOpenService(){
        WxMpService wxMpService=new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxOpenConfigStorage());
        return wxMpService;
    }

    @Bean
    public WxMpConfigStorage wxOpenConfigStorage(){
        WxMpInMemoryConfigStorage wxOpenConfigStorage=new WxMpInMemoryConfigStorage();
        wxOpenConfigStorage.setAppId(wechatAccountProperties.getOpenAppid());
        wxOpenConfigStorage.setSecret(wechatAccountProperties.getOpenSecret());
        return wxOpenConfigStorage;
    }
}
