package com.zxing.sell.service;

import com.zxing.sell.model.SellerInfo;

/**
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
public interface SellerService {
    SellerInfo findByOpenid(String openid);
}
