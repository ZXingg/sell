package com.zxing.sell.controller;

import com.zxing.sell.config.ProjectUrlProperties;
import com.zxing.sell.enums.ResultEnum;
import com.zxing.sell.exception.SellException;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.result.WxMpOAuth2AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by ZXing at 2018/2/13
 * QQ:1490570560
 */
@Controller //重定向   RestController只能返回json
@RequestMapping("/wechat")
@Slf4j
public class WeChatController {

    @Autowired  //配置
    private WxMpService wxMpService;

    @Autowired
    private WxMpService wxOpenService;

    @Autowired
    private ProjectUrlProperties projectUrlProperties;

    @GetMapping("/authorize")
    public String auth(@RequestParam("returnUrl") String returnUrl) {
        log.info("进入authorize");
        //配置
        //获取code
        String url = projectUrlProperties.getMpAuthorizeUrl()+"/sell/wechat/userInfo";
        String redirectUrl = null;
        try {
            returnUrl = URLEncoder.encode(returnUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("【微信网页授权】returnUrl转码异常 " + e.getMessage());
        }
        redirectUrl = wxMpService.oauth2buildAuthorizationUrl(url, WxConsts.OAuth2Scope.SNSAPI_USERINFO, returnUrl);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("/userInfo")
    public String userInfo(@RequestParam("code") String code,
                           @RequestParam("state") String returnUrl) {
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxMpService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页授权】错误 " + e);
            throw new SellException(ResultEnum.WECHAT_MP_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();
        log.info("【openid】- {}",openid);

        return "redirect:" + returnUrl + "?openid=" + openid;

    }

    //returnUrl= http://127.0.0.1/sell/seller/user/login
    @GetMapping("/qrAuthorize")
    public String qrAuth(@RequestParam("returnUrl") String returnUrl) {
        String url = projectUrlProperties.getOpenAuthorizeUrl()+"/sell/wechat/qrUserInfo";
        String redirectUrl = null;
        try {
            returnUrl = URLEncoder.encode(returnUrl, "utf-8");
        } catch (UnsupportedEncodingException e) {
            log.error("【微信网页扫码授权】returnUrl转码异常 " + e.getMessage());
        }
        redirectUrl = wxOpenService.buildQrConnectUrl(url, WxConsts.QrConnectScope.SNSAPI_LOGIN, returnUrl);

        return "redirect:" + redirectUrl;
    }

    @GetMapping("qrUserInfo")
    public String qrUserInfo(@RequestParam("code") String code,
                             @RequestParam("state") String returnUrl){
        WxMpOAuth2AccessToken wxMpOAuth2AccessToken;
        try {
            wxMpOAuth2AccessToken = wxOpenService.oauth2getAccessToken(code);
        } catch (WxErrorException e) {
            log.error("【微信网页扫码授权】错误 " + e);
            throw new SellException(ResultEnum.WECHAT_OPEN_ERROR.getCode(), e.getError().getErrorMsg());
        }
        String openid = wxMpOAuth2AccessToken.getOpenId();

        //returnUrl= http://127.0.0.1/sell/seller/user/login
        return "redirect:" + returnUrl + "?openid=" + openid;
    }
}
