package com.zxing.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 * Created by ZXing at 2018/2/12
 * QQ:1490570560
 */
@Getter
@AllArgsConstructor
public enum PayStatusEnum implements CodeEnum{
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功"),
    ;

    private Integer code;
    private String msg;
}
