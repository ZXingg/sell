package com.zxing.sell.repository;

import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.model.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
@Repository
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    OrderMaster findByOrderId(String orderId);

    Page<OrderMaster> findByBuyerOpenid(String openid,Pageable pageable);


}
