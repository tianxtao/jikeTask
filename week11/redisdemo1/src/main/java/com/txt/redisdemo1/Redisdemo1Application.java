package com.txt.redisdemo1;

import com.txt.redisdemo1.subpub.RedisMsgPubSubListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.Jedis;

@SpringBootApplication
public class Redisdemo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Redisdemo1Application.class, args);
        subjava();
    }

    public static void subjava() {
        Jedis jr = null;
        try {
            jr = new Jedis("127.0.0.1", 6379, 0);// redis服务地址和端口号
            RedisMsgPubSubListener sp = new RedisMsgPubSubListener();
            jr.subscribe(sp, "handlerOrder");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jr != null) {
                jr.disconnect();
            }
        }
    }
}
