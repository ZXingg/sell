package com.zxing.sell.repository;

import com.zxing.sell.model.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Test
    public void addOne(){
        ProductCategory productCategory=productCategoryRepository.save(new ProductCategory("儿童最爱",4));
        Assert.assertNotNull(productCategory);
//        Assert.assertNotEquals(null,productCategory);
    }

    @Test
    public void updOne(){
        ProductCategory productCategory=productCategoryRepository.findOne(2);
        productCategory.setCategoryName("女生最爱");
        productCategoryRepository.save(productCategory);
    }

    @Test
    public void findOne(){
        System.out.println(productCategoryRepository.findOne(1));
    }

    @Test
    public void findByCategoryTypeIn(){
        List<Integer> types= Arrays.asList(1,2,3);
        List<ProductCategory> productCategories=productCategoryRepository.findByCategoryTypeIn(types);
        Assert.assertNotEquals(0,productCategories.size());
    }
}