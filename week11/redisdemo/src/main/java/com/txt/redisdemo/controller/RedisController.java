package com.txt.redisdemo.controller;

import com.txt.redisdemo.util.RedisCountUtil;
import com.txt.redisdemo.util.RedisLockUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Description :
 * @Date : 2022-03-26
 */
@RestController
public class RedisController {
    @Resource
    RedisLockUtil redisLockUtil;
    @Resource
    RedisCountUtil redisCountUtil;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/buy")
    public String buy(@RequestParam String goodId) {
        long timeout = 15;
        TimeUnit timeUnit = TimeUnit.SECONDS;
        // UUID 作为 value
        String lockValue = UUID.randomUUID().toString();
        if (redisLockUtil.lock(goodId, lockValue, timeout, timeUnit)) {
            // 业务处理
            logger.info("获得锁，进行业务处理");
            try {
                // 休眠 10 秒钟
                Thread.sleep(60 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 释放锁
            if (redisLockUtil.unlock(goodId, lockValue)) {
                logger.error("redis分布式锁解锁异常 key 为" + goodId);
            }
            return "购买成功";
        }
        return "请稍后再试";
    }

    /**
     * @Description: 减库存
     *
     * @param
     * @return
     * @Date: 2022/3/27
     */
    @RequestMapping("/decrStock")
    public String decr(@RequestParam String stock, @RequestParam int co) {
        int currentCount = redisCountUtil.get(stock);
        CountDownLatch countDownLatch = new CountDownLatch(co);
        StringBuffer sb = new StringBuffer("开始库存:" + currentCount + "; ");

        for (int i=0;i<co;i++){
            new Thread(() -> {
                if(redisCountUtil.get(stock) > 0) {
                    int count = redisCountUtil.decr(stock);
                    sb.append("当前库存:" + count + "; ");
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


}
