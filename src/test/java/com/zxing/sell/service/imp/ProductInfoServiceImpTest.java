package com.zxing.sell.service.imp;

import com.zxing.sell.enums.ProductInfoStatusEnum;
import com.zxing.sell.model.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.awt.print.Pageable;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImpTest {
    @Autowired
    private ProductInfoServiceImp productInfoServiceImp;

    @Test
    public void save() throws Exception {
        ProductInfo productInfo = new ProductInfo("123", "皮蛋粥", new BigDecimal(3.2), 100, "好喝的皮蛋粥。。", "http://dream96.top/pdz.png", ProductInfoStatusEnum.UP.getStatus(), 2);
        productInfoServiceImp.save(productInfo);
        ProductInfo productInfo1 = new ProductInfo("145", "皮皮虾", new BigDecimal(5.2), 100, "好吃的皮皮虾。。", "http://dream96.top/ppx.png", ProductInfoStatusEnum.DOWN.getStatus(), 1);
        productInfoServiceImp.save(productInfo1);
    }

    @Test
    public void findOne() throws Exception {
        Assert.assertNotNull(productInfoServiceImp.findOne("123"));
    }

    @Test
    public void findUpAll() throws Exception {
        Assert.assertNotEquals(0,productInfoServiceImp.findUpAll());
    }

    @Test
    public void findAll() throws Exception {
        PageRequest pageRequest=new PageRequest(0,2);
        Page res=productInfoServiceImp.findAll(pageRequest);
        System.out.println(res);
        Assert.assertNotEquals(0,res.getTotalElements());
    }

}