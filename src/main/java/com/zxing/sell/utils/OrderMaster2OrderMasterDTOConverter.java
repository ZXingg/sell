package com.zxing.sell.utils;

import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.model.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ZXing at 2018/2/12
 * QQ:1490570560
 */
public class OrderMaster2OrderMasterDTOConverter {
    public static OrderMasterDTO conver(OrderMaster orderMaster){
        OrderMasterDTO orderMasterDTO=new OrderMasterDTO();
        BeanUtils.copyProperties(orderMaster,orderMasterDTO);
        return orderMasterDTO;
    }

    public static List<OrderMasterDTO> converList(List<OrderMaster> orderMasters){
        return orderMasters.stream().map(e->conver(e)).collect(Collectors.toList());
    }
}
