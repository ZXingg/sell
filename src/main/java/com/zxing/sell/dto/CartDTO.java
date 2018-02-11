package com.zxing.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 用于修改库存
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
@Data
@AllArgsConstructor
public class CartDTO {
    private String productId;
    private Integer productQuantity;
}
