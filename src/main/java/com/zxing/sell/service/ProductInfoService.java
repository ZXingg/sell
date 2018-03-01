package com.zxing.sell.service;

import com.zxing.sell.dto.CartDTO;
import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.model.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
public interface ProductInfoService {
    ProductInfo findOne(String id);
    Page<ProductInfo> findAll(Pageable pageable);
    ProductInfo save(ProductInfo productInfo);
    /**
     * 查询在架商品
     * @param
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 加库存
     * @param cartDTOS
     */
    void increaseStock(List<CartDTO> cartDTOS);

    /**
     * 减库存
     * @param cartDTOS
     */
    void decreaseStock(List<CartDTO> cartDTOS);

    /**
     * 上架
     * @param productId
     * @return
     */
    ProductInfo onSale(String productId);

    /**
     * 下架
     * @param productId
     * @return
     */
    ProductInfo offSale(String productId);
}
