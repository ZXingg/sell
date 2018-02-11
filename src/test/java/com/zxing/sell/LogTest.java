package com.zxing.sell;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by ZXing at 2018/2/10
 * QQ:1490570560
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogTest {

    private Logger logger= LoggerFactory.getLogger(LogTest.class);

    @Test
    public void test(){
        logger.error("error...");
        logger.warn("warn...");
        logger.info("info...");
        logger.debug("debug...");
        logger.trace("trace..");
    }
}
