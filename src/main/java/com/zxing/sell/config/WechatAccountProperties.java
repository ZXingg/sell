package com.zxing.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by ZXing at 2018/2/13
 * QQ:1490570560
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountProperties {
    /**
     * 设置微信公众号的appid
     */
    private String mpAppid;
    /**
     * 设置微信公众号的secret
     */
    private String mpSecret;

    /**
     * 设置微信开放平台的appid
     */
    private String openAppid;
    /**
     * 设置微信开放平台的secret
     */
    private String openSecret;
    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
     */
    private String subAppId;

    /**
     * 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
     */
    private String subMchId;

    /**
     * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
     */
    private String keyPath;

    /**
     * 模板消息
     */
    private Map<String,String> templateId;
}
