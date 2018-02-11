package com.zxing.sell.service.imp;

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
public class ProductCategoryServiceImpTest {
    @Autowired
    private ProductCategoryServiceImp productCategoryServiceImp;

    @Test
    public void findOne() throws Exception {
        ProductCategory productCategory = productCategoryServiceImp.findOne(1);
        System.out.println(productCategory.toString());
        Assert.assertEquals(new Integer(1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        Assert.assertNotEquals(0, productCategoryServiceImp.findAll().size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception {
        List<Integer> types= Arrays.asList(1,2,3);
        List<ProductCategory> productCategories= productCategoryServiceImp.findByCategoryTypeIn(types);
        Assert.assertNotEquals(0,productCategories.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory= productCategoryServiceImp.save(new ProductCategory("儿童专区",5));
        Assert.assertNotNull(productCategory);
    }

}