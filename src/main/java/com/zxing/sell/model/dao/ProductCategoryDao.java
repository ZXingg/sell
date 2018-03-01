package com.zxing.sell.model.dao;

import com.zxing.sell.model.ProductCategory;
import com.zxing.sell.model.mapper.ProductCategoryMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 测试使用mybatis操作数据库
 * Created by ZXing at 2018/2/24
 * QQ:1490570560
 */
@Component
public class ProductCategoryDao {
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    public int insertByMap(Map<String, Object> map) {
        return productCategoryMapper.insertByMap(map);
    }

    //关键字段对应ProductCategory属性
    public int insertByObject(ProductCategory productCategory) {
        return productCategoryMapper.insertByObject(productCategory);
    }

    public ProductCategory findByType(Integer categoryType) {
        return productCategoryMapper.findByType(categoryType);
    }

    public int updateByType(String categoryName, Integer categoryType) {
        return productCategoryMapper.updateByType(categoryName, categoryType);
    }

    public int updateByObject(ProductCategory productCategory) {
        return productCategoryMapper.updateByObject(productCategory);
    }

    public int deleteByType(Integer categoryType) {
        return productCategoryMapper.deleteByType(categoryType);
    }

    public ProductCategory selectByCategoryType(Integer categoryType) {
        return productCategoryMapper.selectByCategoryType(categoryType);
    }
}
