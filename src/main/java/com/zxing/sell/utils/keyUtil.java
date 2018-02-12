package com.zxing.sell.utils;

import java.util.Random;

/**
 * Created by ZXing at 2018/2/12
 * QQ:1490570560
 */
public class keyUtil {
    public static synchronized String createUniqueKey(){
        return System.currentTimeMillis()+String.valueOf(new Random().nextInt(900000)+100000);
    }
}
