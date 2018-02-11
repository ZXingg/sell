package com.zxing.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ZXing at 2018/2/10
 * QQ:1490570560
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
//@Data
public class LombokTest {
    @Test
    public void test(){
        String name="zx";
        String pwd="123";
        log.info("name:{},pwd:{}",name,pwd);
        log.error("error...");
        log.warn("warn...");
        log.info("info...");
        log.debug("debug...");
        log.trace("trace..");
    }
}
