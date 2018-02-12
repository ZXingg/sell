package com.zxing.sell.service.imp;

import com.zxing.sell.dto.CartDTO;
import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.enums.OrderMasterEnum;
import com.zxing.sell.model.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImpTest {
    private final String BUYER_OPENID="123xxx";
    private final String ORDERID="1518437330842526434";
    @Autowired
    private OrderServiceImp orderServiceImp;

    @Test
    public void create() throws Exception {
        List<OrderDetail> orderDetails=new ArrayList<>();
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setProductId("123");
        orderDetail.setProductQuantity(2);
        orderDetails.add(orderDetail);

        OrderDetail orderDetail1=new OrderDetail();
        orderDetail1.setProductId("145");
        orderDetail1.setProductQuantity(2);
        orderDetails.add(orderDetail1);

        OrderMasterDTO orderMasterDTO=new OrderMasterDTO("zxx","12345678901"
                ,"广州",BUYER_OPENID,orderDetails);

        OrderMasterDTO result=orderServiceImp.create(orderMasterDTO);
        log.info("【创建订单】result={}",result);

    }

    @Test
    public void findOne() throws Exception {
        OrderMasterDTO orderMasterDTO=orderServiceImp.findOne(ORDERID);
        log.info("【查询单个订单】result：{}",orderMasterDTO);
        Assert.assertEquals(ORDERID,orderMasterDTO.getOrderId());
    }
    @Test
    public void findList() throws Exception {
        Page page=orderServiceImp.findList(BUYER_OPENID,new PageRequest(0,2));
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void cancle() throws Exception {
        OrderMasterDTO orderMasterDTO=orderServiceImp.findOne("1518437905625155954");
        OrderMasterDTO result=orderServiceImp.cancle(orderMasterDTO);
        Assert.assertEquals(OrderMasterEnum.CANCLE.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception {
    }

    @Test
    public void paid() throws Exception {
    }

}