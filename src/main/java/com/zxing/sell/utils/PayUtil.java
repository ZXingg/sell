package com.zxing.sell.utils;

import java.math.BigDecimal;

/**
 * 支付
 * Created by ZXing at 2018/2/15
 * QQ:1490570560
 */
public class PayUtil {
    /**
     * 元转为分
     * @param yuan
     * @return
     */
    public static String yuanToFee(BigDecimal yuan){
        return yuan.multiply(new BigDecimal(100)).toString();
    }
}
