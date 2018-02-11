package com.zxing.sell.service.imp;

import com.zxing.sell.enums.ProductInfoStatusEnum;
import com.zxing.sell.model.ProductInfo;
import com.zxing.sell.repository.ProductInfoRepository;
import com.zxing.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
@Service
public class ProductInfoServiceImp implements ProductInfoService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String id) {
        return productInfoRepository.findOne(id);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductInfoStatusEnum.UP.getStatus());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }
}
