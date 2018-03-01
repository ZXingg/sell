package com.zxing.sell.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by ZXing at 2018/2/25
 * QQ:1490570560
 */
@Component
@Slf4j
public class RedisLockUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁
     * 当前线程能否获得锁 进入秒杀活动
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        //获得锁
        if (stringRedisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        String currentValue = stringRedisTemplate.opsForValue().get(key);
        //检测锁是否过期 避免因异常发生死锁
        if (!StringUtils.isEmpty(currentValue) && Long.valueOf(currentValue) < System.currentTimeMillis()) {
            String oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue) && oldValue.equals(currentValue)) {//否则锁已被它人占用
                return true;
            }
        }
        //未获得锁
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unlock(String key, String value) {
        try {
            String currentValue = stringRedisTemplate.opsForValue().get(key);
            if (StringUtils.isEmpty(currentValue) && currentValue.equals(value)) {
                stringRedisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】解锁异常，{}", e);
        }
    }
}
