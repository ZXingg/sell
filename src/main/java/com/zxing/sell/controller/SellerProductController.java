package com.zxing.sell.controller;

import com.zxing.sell.VO.ResultVO;
import com.zxing.sell.enums.ResultEnum;
import com.zxing.sell.exception.SellException;
import com.zxing.sell.model.ProductCategory;
import com.zxing.sell.model.ProductInfo;
import com.zxing.sell.service.imp.ProductCategoryServiceImp;
import com.zxing.sell.service.imp.ProductInfoServiceImp;
import com.zxing.sell.utils.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by ZXing at 2018/2/20
 * QQ:1490570560
 */
@Controller
@RequestMapping("/seller/product")
public class SellerProductController {
    @Autowired
    private ProductInfoServiceImp productInfoServiceImp;

    @Autowired
    private ProductCategoryServiceImp categoryServiceImp;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        Page productInfoPage = productInfoServiceImp.findAll(new PageRequest(page - 1, size));
        map.put("productInfoPage", productInfoPage);
        map.put("currentPage", page);

        return new ModelAndView("product/list", map);
    }

    @GetMapping("/on_sale")
    @ResponseBody
    public ResultVO onSale(@RequestParam("productId") String productId) {
        try {
            productInfoServiceImp.onSale(productId);
        } catch (SellException e) {
            return ResultVOUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVOUtil.success();
    }

    @GetMapping("/off_sale")
    @ResponseBody
    public ResultVO offSale(@RequestParam("productId") String productId) {
        try {
            productInfoServiceImp.offSale(productId);
        } catch (SellException e) {
            return ResultVOUtil.error(e.getCode(), e.getMessage());
        }
        return ResultVOUtil.success();
    }

    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "productId", required = false) String productId,
                              Map<String, Object> map) {
        if (!StringUtils.isEmpty(productId)) {
            ProductInfo productInfo = productInfoServiceImp.findOne(productId);
            map.put("productInfo", productInfo);
        }
        List<ProductCategory> productCategoryList = categoryServiceImp.findAll();
        map.put("productCategoryList", productCategoryList);

        return new ModelAndView("product/index", map);
    }

    @PostMapping("/save")
    @ResponseBody
    @CacheEvict(cacheNames = "products", key = "123")//清除缓存
    public ResultVO save(@Valid ProductInfo productInfo,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR);
        }
        ProductInfo productInfo1 = productInfoServiceImp.save(productInfo);
        if (productInfo1 == null) {
            return ResultVOUtil.error(ResultEnum.PRODUCT_UPDATE_ERROR);
        }

        return ResultVOUtil.success();
    }
}
