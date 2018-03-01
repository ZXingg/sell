package com.zxing.sell.service.imp;

import com.zxing.sell.dto.OrderMasterDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PushMessageServiceImpTest {
    @Autowired
    private PushMessageServiceImp pushMessageServiceImp;
    @Autowired
    private OrderServiceImp orderServiceImp;

    @Test
    public void orderStatusUpd() throws Exception {
        OrderMasterDTO orderMasterDTO=orderServiceImp.findOne("1518437330842526434");
        pushMessageServiceImp.orderStatusUpd(orderMasterDTO);
    }

}