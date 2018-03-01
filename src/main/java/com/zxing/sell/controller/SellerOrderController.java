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
@RequestMapping("/seller/order")
@Slf4j
public class SellerOrderController {
    @Autowired
    private OrderServiceImp orderServiceImp;

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                             Map<String, Object> map) {

        Page orderDTOPage = orderServiceImp.findList(new PageRequest(page - 1, size));
        map.put("orderDTOPage", orderDTOPage);
        map.put("currentPage", page);

        return new ModelAndView("order/list", map);
    }



    @GetMapping("/cancel")
    @ResponseBody
    public ResultVO cancle(@RequestParam("orderId") String orderId) {
        try {
            OrderMasterDTO orderMasterDTO = orderServiceImp.findOne(orderId);
            orderServiceImp.cancle(orderMasterDTO);
            if (orderMasterDTO == null) {
                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            }
        } catch (SellException e) {
            log.error("【卖家端取消订单】发生异常，e={}", e.getMessage());
            return ResultVOUtil.error(e.getCode(),e.getMessage());
        }
        return ResultVOUtil.success();
    }

//    @GetMapping("/detail")
//    @ResponseBody
//    public ResultVO<OrderMasterDTO> detail(@RequestParam("orderId") String orderId){
//        OrderMasterDTO orderMasterDTO;
//        try {
//            orderMasterDTO= orderServiceImp.findOne(orderId);
//            if (orderMasterDTO == null) {
//                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
//            }
//        } catch (SellException e) {
//            log.error("【买家端取消订单】发生异常，e={}", e.getMessage());
//            return ResultVOUtil.error(e.getCode(),e.getMessage());
//        }
//        return ResultVOUtil.success(orderMasterDTO);
//    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderMasterDTO orderMasterDTO = null;
        try {
            orderMasterDTO= orderServiceImp.findOne(orderId);
            if (orderMasterDTO == null) {
                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            }
        } catch (SellException e) {
            log.error("【卖家端查询订单详情】发生异常，e={}", e.getMessage());
            map.put("error",e.getMessage());
        }
        map.put("orderDTO",orderMasterDTO);

        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    @ResponseBody
    public ResultVO finish(@RequestParam("orderId") String orderId) {
        try {
            OrderMasterDTO orderMasterDTO = orderServiceImp.findOne(orderId);
            orderServiceImp.finish(orderMasterDTO);
            if (orderMasterDTO == null) {
                throw new SellException(ResultEnum.ORDER_NOT_EXIST);
            }
        } catch (SellException e) {
            log.error("【卖家端完结订单】发生异常，e={}", e.getMessage());
            return ResultVOUtil.error(e.getCode(),e.getMessage());
        }
        return ResultVOUtil.success();
    }

}
