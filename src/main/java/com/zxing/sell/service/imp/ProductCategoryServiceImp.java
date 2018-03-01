package com.zxing.sell.service.imp;

import com.zxing.sell.model.ProductCategory;
import com.zxing.sell.repository.ProductCategoryRepository;
import com.zxing.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZXing at 2018/2/10
 * QQ:1490570560
 */
@Service
public class ProductCategoryServiceImp implements ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public ProductCategory findOne(Integer id) {
        return productCategoryRepository.findOne(id);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }

    @Override
    public Page<ProductCategory> findAll(Pageable pageable) {
        return productCategoryRepository.findAll(pageable);
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> types) {
        return productCategoryRepository.findByCategoryTypeIn(types);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}
