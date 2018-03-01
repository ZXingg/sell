package com.zxing.sell.controller;

import com.zxing.sell.VO.ResultVO;
import com.zxing.sell.dto.OrderMasterDTO;
import com.zxing.sell.enums.ResultEnum;
import com.zxing.sell.exception.SellException;
import com.zxing.sell.service.imp.OrderServiceImp;
import com.zxing.sell.utils.ResultVOUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * Created by ZXing at 2018/2/15
 * QQ:1490570560
 */
@Controller
@RequestMapping("/seller/aspect")
public class SellerAspectTestController {

    @GetMapping("/test")
    public ModelAndView list() {
        return new ModelAndView("order/list");
    }

}
