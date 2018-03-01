package com.zxing.sell.controller;

import com.zxing.sell.service.imp.SecKillServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ZXing at 2018/2/25
 * QQ:1490570560
 */
@RestController
@RequestMapping("/secKill")
public class SecKillController {

    @Autowired
    private SecKillServiceImp secKillServiceImp;

    @GetMapping("/query/{productId}")
    public String query(@PathVariable("productId") String productId) {
        return secKillServiceImp.querySecKillProductInfo(productId);
    }

    @GetMapping("/order/{productId}")
    public String order(@PathVariable("productId") String productId) {
        System.out.println("@--SecKill-- productId : " + productId);
        secKillServiceImp.secKill(productId);
        return secKillServiceImp.querySecKillProductInfo(productId);
    }

}
