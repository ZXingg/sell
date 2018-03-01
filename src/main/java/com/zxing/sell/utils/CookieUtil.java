package com.zxing.sell.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
public class CookieUtil {
    public static void set(HttpServletResponse response, String name, String value, Integer expire) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(expire);
        response.addCookie(cookie);
    }

    public static Cookie get(HttpServletRequest request, String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie :cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }

}
