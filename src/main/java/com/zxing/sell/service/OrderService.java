package com.zxing.sell.service;

import com.zxing.sell.dto.OrderMasterDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by ZXing at 2018/2/12
 * QQ:1490570560
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO create(OrderMasterDTO orderMasterDTO);

    /**
     * 查找单个订单
     * @param orderId
     * @return
     */
    OrderMasterDTO findOne(String orderId);

    /**
     * 查找多个订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMasterDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 查询所有
     * @param pageable
     * @return
     */
    Page<OrderMasterDTO> findList(Pageable pageable);

    /**
     * 取消订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO cancle(OrderMasterDTO orderMasterDTO);

    /**
     * 完结订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO finish(OrderMasterDTO orderMasterDTO);

    /**
     * 支付订单
     * @param orderMasterDTO
     * @return
     */
    OrderMasterDTO paid(OrderMasterDTO orderMasterDTO);


}
