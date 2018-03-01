package com.zxing.sell.service.imp;

import com.zxing.sell.exception.SellException;
import com.zxing.sell.service.SecKillService;
import com.zxing.sell.utils.RedisLockUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZXing at 2018/2/25
 * QQ:1490570560
 */
@Service
public class SecKillServiceImp implements SecKillService {
    private static final int TIMEOUT = 500;//锁超时时间0.5秒
    private static Map<String, Integer> products;//商品总数
    private static Map<String, Integer> stocks;//库存
    private static Map<String, Integer> orders;//已下单数

    static {
        products = new HashMap<>();
        stocks = new HashMap<>();
        orders = new HashMap<>();
        products.put("12345", 10000);
        stocks.put("12345", 10000);
        orders.put("12345", 0);
    }

    @Autowired
    private RedisLockUtil redisLockUtil;

    private String queryMap(String productId) {
        return "国庆活动，皮蛋粥特价，限量：" + products.get(productId) + " 份,"
                + " 剩余：" + stocks.get(productId) + " 份,"
                + " 已抢：" + orders.get(productId) + " 份。";
    }

    @Override
    public String querySecKillProductInfo(String productId) {
        return queryMap(productId);
    }

    @Override
    //加synchronized 解决单节点服务器并发问题
    //使用redis分布式锁
    public void secKill(String productId) {
        //加锁
        String time = String.valueOf(System.currentTimeMillis() + TIMEOUT);
        if (!redisLockUtil.lock(productId, time)) {//加锁失败
            throw new SellException(101, "哎呦喂，人也太多了吧，换个姿势再试试~~");
        }
        ;
        //查库存
        int stockNum = stocks.get(productId);
        if (stockNum <= 0) {
            throw new SellException(100, "活动已结束！");
        }
        //下单
        int orderNum = orders.get(productId);
        orders.put(productId, orderNum + 1);
        //减库存
        stockNum -= 1;
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stocks.put(productId, stockNum);

        //解锁
        redisLockUtil.unlock(productId, time);
    }
}
