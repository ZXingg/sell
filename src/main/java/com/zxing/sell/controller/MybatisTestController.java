package com.zxing.sell.controller;

import com.zxing.sell.model.ProductCategory;
import com.zxing.sell.model.dao.ProductCategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZXing at 2018/2/24
 * QQ:1490570560
 */
@RestController
@RequestMapping("/mybatis")
public class MybatisTestController {
    @Autowired
    private ProductCategoryDao productCategoryDao;
    // private ProductCategoryMapper productCategoryMapper;//编译器提示错误 不影响

    @GetMapping("/iByMap")
    public int insertByMap() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryName", "狗狗最爱");
        map.put("categoryType", 10);
        return productCategoryDao.insertByMap(map);
    }

    @GetMapping("/iByObj")
    public int insertByObject() throws Exception {
        ProductCategory productCategory = new ProductCategory("猫咪最爱", 11);
        return productCategoryDao.insertByObject(productCategory);
    }

    @GetMapping("/fByType/{type}")
    public ProductCategory findByType(@PathVariable("type") Integer type){
        return productCategoryDao.findByType(type);
    }

    @GetMapping("/uByType/{type}/{name}")
    public int updateByType(@PathVariable("type") Integer type,@PathVariable("name")String name){
        return productCategoryDao.updateByType(name,type);
    }

    @GetMapping("/uByObj")
    public int updateByObj(ProductCategory productCategory){
        System.out.println(productCategory.toString());
        return productCategoryDao.updateByObject(productCategory);
    }

    @GetMapping("/dByType/{type}")
    public int deleteByType(@PathVariable("type")Integer type){
        return productCategoryDao.deleteByType(type);
    }

    //xml配置测试
    @GetMapping("/sByType/{type}")
    public ProductCategory selectByType(@PathVariable("type") Integer type){
        return productCategoryDao.selectByCategoryType(type);
    }


}
