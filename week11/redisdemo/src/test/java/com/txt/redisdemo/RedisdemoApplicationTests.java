package com.txt.redisdemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import redis.clients.jedis.Jedis;

@SpringBootTest
class RedisdemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    public void pubjava() {
        Jedis jr = null;
        try {
            jr = new Jedis("127.0.0.1", 6379, 0);// redis服务地址和端口号
            jr.publish("handlerOrder", "com.txt.redisdemo.service.OrderService.handlerOrder");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jr != null) {
                jr.disconnect();
            }
        }
    }

    @Test
    public void subjava() {

    }
}
