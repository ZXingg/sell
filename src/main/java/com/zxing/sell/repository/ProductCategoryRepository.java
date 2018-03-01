package com.zxing.sell.repository;

import com.zxing.sell.model.ProductCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *商品类目DAO
 * Created by ZXing at 2018/2/10
 * QQ:1490570560
 */
@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer>{
    List<ProductCategory> findByCategoryTypeIn(List<Integer> types);
}
