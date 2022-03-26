package com.txt.redisdemo1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

/**
 * @Description :
 * @Date : 2022-03-27
 */
@RestController
public class RedisController {

    @GetMapping("/pubMessage")
    public String pub(@RequestParam String message){
        Jedis jr = null;
        try {
            jr = new Jedis("127.0.0.1", 6379, 0);// redis服务地址和端口号
            jr.publish( message, "com.txt.redisdemo1.service.OrderService.handlerOrder");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jr != null) {
                jr.disconnect();
            }
        }
        return "发布完成。";
    }
}
