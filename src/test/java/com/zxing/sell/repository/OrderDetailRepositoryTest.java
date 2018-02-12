package com.zxing.sell.repository;

import com.zxing.sell.model.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Test
    public void findByOrderId() throws Exception {
        OrderDetail orderDetail=new OrderDetail("123","12345","123","皮蛋粥"
                ,new BigDecimal(3.2),4,"http://dream96.top/pdz.png");
        OrderDetail orderDetail1=new OrderDetail("1234","12345","145","皮皮虾"
                ,new BigDecimal(5.2),10,"http://dream96.top/ppx.png");

        Assert.assertNotNull(orderDetailRepository.save(orderDetail));
        Assert.assertNotNull(orderDetailRepository.save(orderDetail1));
    }

}