package com.zxing.sell.service;

import com.zxing.sell.model.ProductCategory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZXing at 2018/2/10
 * QQ:1490570560
 */
public interface ProductCategoryService {
    ProductCategory findOne(Integer id);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
    ProductCategory save(ProductCategory productCategory);
}
