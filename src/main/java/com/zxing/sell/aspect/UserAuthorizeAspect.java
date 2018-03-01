package com.zxing.sell.aspect;

import com.zxing.sell.constant.CookieConstant;
import com.zxing.sell.constant.RedisConstant;
import com.zxing.sell.exception.SellAuthorizeException;
import com.zxing.sell.utils.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
@Aspect
@Component
@Slf4j
public class UserAuthorizeAspect {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    //没有企业开放平台账户 暂不拦截
//    @Pointcut("execution(public * com.zxing.sell.controller.Seller*.*(..))" +
//            "&& !execution(public * com.zxing.sell.controller.SellerUserController.*(..))")
    @Pointcut("execution(public * com.zxing.sell.controller.SellerAspectTestController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);
        if (cookie == null) {
            log.warn("【登录校检】Cookie:无效token");
            throw new SellAuthorizeException();
        }
        String token = stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(token)) {
            log.warn("【登录校检】Redis:无效token");
            throw new SellAuthorizeException();
        }
    }
}
