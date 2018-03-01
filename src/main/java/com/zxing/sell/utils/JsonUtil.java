package com.zxing.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by ZXing at 2018/2/15
 * QQ:1490570560
 */
public class JsonUtil {
    public static String toJson(Object object){
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson=builder.create();
        return gson.toJson(object);
    }
}
