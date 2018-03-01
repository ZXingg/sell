//package com.zxing.sell.model.mapper;
//
//import com.zxing.sell.model.ProductCategory;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class ProductCategoryMapperTest {
//
//    @Autowired
//    private ProductCategoryMapper productCategoryMapper;
//
//    //测试失败 无法加载websocket容器(运行环境能正确通过）？
//    @Test
//    public void insertByMap() throws Exception {
//        Map<String, Object> map = new HashMap<>();
//        map.put("categoryName", "狗狗最爱");
//        map.put("categoryType", 10);
//        Assert.assertEquals(1, productCategoryMapper.insertByMap(map));
//    }
//
//    @Test
//    public void insertByObject() throws Exception {
//        ProductCategory productCategory = new ProductCategory("猫咪最爱", 11);
//        Assert.assertEquals(1, productCategoryMapper.insertByObject(productCategory));
//    }
//
//}