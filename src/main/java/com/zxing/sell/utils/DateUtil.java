package com.zxing.sell.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZXing at 2018/2/15
 * QQ:1490570560
 */
public class DateUtil {
    public static String format(Date date){
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}
