package com.zxing.sell.repository;

import com.zxing.sell.enums.OrderMasterEnum;
import com.zxing.sell.enums.PayStatusEnum;
import com.zxing.sell.model.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {
    @Autowired
    OrderMasterRepository orderMasterRepository;

    @Test
    public void save() {
        OrderMaster orderMaster = new OrderMaster("12345", "zx", "12345678910"
                , "江西赣州", "123abc", new BigDecimal(6.4)
                , OrderMasterEnum.NEW.getCode(), PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);

        OrderMaster orderMaster1 = new OrderMaster("123456", "zx", "12345678910"
                , "江西赣州", "123abc", new BigDecimal(16.4)
                , OrderMasterEnum.CANCLE.getCode(), PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster1);

        OrderMaster orderMaster2 = new OrderMaster("1234567", "zssx", "12345678910"
                , "北京", "123abc1", new BigDecimal(116.4)
                , OrderMasterEnum.NEW.getCode(), PayStatusEnum.WAIT.getCode());
        Assert.assertNotNull(orderMasterRepository.save(orderMaster2));
    }

    @Test
    public void findByBuyerOpenid() throws Exception {
        PageRequest pageable=new PageRequest(0,3);
        System.out.println(orderMasterRepository.findByBuyerOpenid("123abc",pageable).getTotalElements());
        Assert.assertNotEquals(0,orderMasterRepository.findByBuyerOpenid("123abc",pageable).getTotalElements());
    }

}