package com.txt.redisdemo.util;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description : 分布式计数器
 * @Date : 2022-03-27
 */
@Component
public class RedisCountUtil implements InitializingBean {
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * @Description: 增加
     *
     * @param id
     * @return void
     * @Date: 2022/3/27
     */
    public int incr(String id){
        if(redisTemplate.boundValueOps(id).get()==null){
            redisTemplate.boundValueOps(id).set("1");
        }else{
            redisTemplate.boundValueOps(id).increment();
        }

        return get(id);
    }

    /**
     * @Description: 减少
     *
     * @param id
     * @return void
     * @Date: 2022/3/27
     */
    public synchronized int decr(String id){
        int value = 0;
        if(redisTemplate.boundValueOps(id).get()==null){
            value = 0;
        }else{
            redisTemplate.boundValueOps(id).decrement();
            value = get(id);
        }

        return value;
    }

    public int get(String id){
        return Integer.parseInt(redisTemplate.boundValueOps(id).get());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        redisTemplate.opsForValue().set("stock", "100");
    }
}
