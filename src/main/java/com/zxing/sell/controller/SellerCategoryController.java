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
@RequestMapping("/seller/category")
public class SellerCategoryController {
    @Autowired
    private ProductCategoryServiceImp categoryServiceImp;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        Page categoryPage = categoryServiceImp.findAll(new PageRequest(page - 1, size));
        map.put("categoryPage", categoryPage);
        map.put("currentPage", page);

        return new ModelAndView("category/list", map);
    }

    /*@GetMapping("/on_sale")
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
*/
    @GetMapping("/index")
    public ModelAndView index(@RequestParam(value = "categoryId", required = false) Integer categoryId,
                              Map<String, Object> map) {
        if (categoryId !=null) {
            ProductCategory category = categoryServiceImp.findOne(categoryId);
            map.put("category", category);
        }

        return new ModelAndView("category/index", map);
    }

    @PostMapping("/save")
    @ResponseBody
    public ResultVO save(@Valid ProductCategory category,
                         BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResultVOUtil.error(ResultEnum.PARAM_ERROR);
        }
        ProductCategory category1 = categoryServiceImp.save(category);
        if (category1 == null) {
            return ResultVOUtil.error(ResultEnum.PRODUCT_CATEGORY_UPDATE_ERROR);
        }

        return ResultVOUtil.success();
    }
}
