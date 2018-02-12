package com.zxing.sell.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by ZXing at 2018/2/12
 * QQ:1490570560
 */
@Getter
@AllArgsConstructor
public enum ResultEnum{

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),

    ;
    private Integer code;
    private String msg;
}
