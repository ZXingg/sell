package com.zxing.sell.service.imp;

import com.zxing.sell.model.ProductCategory;
import com.zxing.sell.repository.ProductCatagoryRepository;
import com.zxing.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZXing at 2018/2/10
 * QQ:1490570560
 */
@Service
public class ProductCategoryServiceImp implements ProductCategoryService {
    @Autowired
    private ProductCatagoryRepository productCatagoryRepository;

    @Override
    public ProductCategory findOne(Integer id) {
        return productCatagoryRepository.findOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCatagoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return productCatagoryRepository.findByCategoryTypeIn(types);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCatagoryRepository.save(productCategory);
    }
}
