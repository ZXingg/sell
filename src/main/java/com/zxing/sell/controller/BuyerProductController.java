package com.zxing.sell.controller;

import com.zxing.sell.VO.ProductInfoVO;
import com.zxing.sell.VO.ProductVO;
import com.zxing.sell.VO.ResultVO;
import com.zxing.sell.model.ProductCategory;
import com.zxing.sell.model.ProductInfo;
import com.zxing.sell.service.imp.ProductCategoryServiceImp;
import com.zxing.sell.service.imp.ProductInfoServiceImp;
import com.zxing.sell.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ZXing at 2018/2/11
 * QQ:1490570560
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {
    @Autowired
    private ProductInfoServiceImp productInfoServiceImp;

    @Autowired
    private ProductCategoryServiceImp productCategoryServiceImp;

    @GetMapping("/list")
    //请求参数testKey的长度大于3 且 访问成功结果正确时 缓存本次结果 #result为返回的对象-ResultVO
    @Cacheable(cacheNames = "products", key = "#testKey", condition = "#testKey.length()>3", unless = "#result.getCode()!=0")
    public ResultVO getList(@RequestParam(value = "testKey", required = false, defaultValue = "") String testKey) {
//        ProductInfoVO productInfoVO = new ProductInfoVO("123", "皮蛋粥", new BigDecimal(3.2), "好喝", "pdz.png");
//        ProductVO productVO = new ProductVO("男生最爱", 0, Arrays.asList(productInfoVO));
        //查询所有在架商品
        List<ProductInfo> productInfos = productInfoServiceImp.findUpAll();
        List<Integer> categoryTypes = productInfos.stream()
                .map(e -> e.getCategoryType())
                .collect(Collectors.toList());
        List<ProductCategory> productCategories = productCategoryServiceImp.findByCategoryTypeIn(categoryTypes);
        List<ProductVO> productVOList = new ArrayList<>();
        for (ProductCategory productCategory : productCategories) {
            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            Integer productCategoryType = productCategory.getCategoryType();
            for (ProductInfo productInfo : productInfos) {
                if (productInfo.getCategoryType().equals(productCategoryType)) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            ProductVO productVO = new ProductVO(productCategory.getCategoryName()
                    , productCategory.getCategoryType(), productInfoVOList);
            productVOList.add(productVO);

        }
        return ResultVOUtil.success(productVOList);
    }
}
