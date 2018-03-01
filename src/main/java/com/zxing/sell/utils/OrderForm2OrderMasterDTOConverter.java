package com.zxing.sell.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.enums.ResultEnum;
import com.zxing.sell.exception.SellException;
import com.zxing.sell.form.OrderForm;
import com.zxing.sell.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZXing at 2018/2/13
 * QQ:1490570560
 */
@Slf4j
public class OrderForm2OrderMasterDTOConverter {
    public static OrderMasterDTO conver(OrderForm orderForm){
        List<OrderDetail> orderDetails=new ArrayList<>();
        try{
            orderDetails=new Gson().fromJson(orderForm.getItems()
                    ,new TypeToken<List<OrderDetail>>(){}.getType());
        }catch(Exception e){
            log.error("【对象转换】错误，orderForm:{}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        OrderMasterDTO orderMasterDTO=new OrderMasterDTO(orderForm.getName(),orderForm.getPhone()
                ,orderForm.getAddress(),orderForm.getOpenid(),orderDetails);
        return orderMasterDTO;
    }
}
