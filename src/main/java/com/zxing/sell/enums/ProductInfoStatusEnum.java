package com.zxing.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
@Getter
@AllArgsConstructor
public enum ProductInfoStatusEnum implements CodeEnum{
    UP(0,"在架"),
    DOWN(1,"下架");

    private Integer code;
    private String msg;

}
