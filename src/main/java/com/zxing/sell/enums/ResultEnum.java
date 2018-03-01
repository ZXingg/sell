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

    PARAM_ERROR(1,"参数不正确"),

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(11,"库存不正确"),
    ORDER_NOT_EXIST(12,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),
    ORDER_STATUS_ERROR(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单更新失败"),
    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ORDER_PAY_STATUS_ERROR(17,"订单支付状态不正确"),
    CART_EMPTY(18,"购物车不能为空"),
    ORDER_OWNER_ERROR(19,"该订单不属于当前用户"),
    WECHAT_MP_ERROR(20,"微信公众号方面错误"),
    WECHAT_PAY_ERROR(21,"微信支付订单失败"),
    WECHAT_PAY_NOTIFY_MONEY_VERIFY_ERROR(22,"微信支付异步通知订单金额不一致"),
    WECHAT_OPEN_ERROR(23,"微信开放平台方面错误"),
    PRODUCT_UPDATE_ERROR(24,"商品更新失败"),
    PRODUCT_CATEGORY_UPDATE_ERROR(25,"商品类目更新失败"),
    LOGIN_FAIL(26,"登录失败，登录信息不正确"),

    ;
    private Integer code;
    private String msg;
}
