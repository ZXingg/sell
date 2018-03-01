package com.zxing.sell.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by ZXing at 2018/2/13
 * QQ:1490570560
 */
@Component
@Data
@ConfigurationProperties(prefix = "projectUrl")
public class ProjectUrlProperties {
    /**
     *认证后的回调地址
     */
    private String mpAuthorizeUrl;

    private String openAuthorizeUrl;

    private String sellUrl;


}
