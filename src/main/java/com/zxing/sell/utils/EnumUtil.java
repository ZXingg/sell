package com.zxing.sell.utils;

import com.zxing.sell.enums.CodeEnum;

/**
 * Created by ZXing at 2018/2/15
 * QQ:1490570560
 */
public class EnumUtil<T> {
    public static <T extends CodeEnum>T getByCode(Integer code,Class<T> enumClass){
        for (T each:enumClass.getEnumConstants()){
            if (code.equals(each.getCode())){
                return each;
            }
        }
        return null;
    }
}
