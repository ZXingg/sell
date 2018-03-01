package com.zxing.sell.service;

import com.zxing.sell.dto.OrderMasterDTO;

/**
 * 消息推送
 * Created by ZXing at 2018/2/22
 * QQ:1490570560
 */
public interface PushMessageService {
    /**
     * 订单状态变更
     * @param orderMasterDTO
     */
    void orderStatusUpd(OrderMasterDTO orderMasterDTO);
}
