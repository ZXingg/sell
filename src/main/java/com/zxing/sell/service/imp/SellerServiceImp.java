package com.zxing.sell.service.imp;

import com.zxing.sell.model.SellerInfo;
import com.zxing.sell.repository.SellerRepository;
import com.zxing.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
@Service
public class SellerServiceImp implements SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public SellerInfo findByOpenid(String openid) {
        return sellerRepository.findByOpenid(openid);
    }
}
