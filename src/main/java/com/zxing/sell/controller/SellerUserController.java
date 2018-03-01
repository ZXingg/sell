package com.zxing.sell.controller;

import com.zxing.sell.config.ProjectUrlProperties;
import com.zxing.sell.constant.CookieConstant;
import com.zxing.sell.constant.RedisConstant;
import com.zxing.sell.enums.ResultEnum;
import com.zxing.sell.exception.SellException;
import com.zxing.sell.model.SellerInfo;
import com.zxing.sell.service.imp.SellerServiceImp;
import com.zxing.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
@Controller
@Slf4j
@RequestMapping("/seller")
public class SellerUserController {

    @Autowired
    private SellerServiceImp sellerServiceImp;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ProjectUrlProperties projectUrlProperties;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String, Object> map) {
        //已注册的sell微信管理员则通过验证 未写注册代码
        SellerInfo sellerInfo = sellerServiceImp.findByOpenid(openid);
        try {
            if (sellerInfo == null) {
                throw new SellException(ResultEnum.LOGIN_FAIL);
            }
        } catch (SellException e) {
            map.put("error", e.getMessage());
            return new ModelAndView("index", map);
        }

        String token = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX, token), openid
                , RedisConstant.EXPIRE, TimeUnit.SECONDS);

        CookieUtil.set(response, CookieConstant.TOKEN, token, CookieConstant.EXPIRE);
        //redirect 使用绝对地址
        return new ModelAndView("redirect:" + projectUrlProperties.getSellUrl() + "/sell/seller/order/list", map);
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request
            , HttpServletResponse response
            , Map<String, Object> map) {
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie != null) {
            //清除token
            stringRedisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
            CookieUtil.set(response, CookieConstant.TOKEN, null, 0);
        }

        return new ModelAndView("index",map);
    }
}
