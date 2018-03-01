package com.zxing.sell.repository;

import com.zxing.sell.model.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
@Repository
public interface SellerRepository extends JpaRepository<SellerInfo, String> {
    SellerInfo findByOpenid(String openid);
}
