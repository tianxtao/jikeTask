package com.txt.redisdemo1.subpub;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.JedisPubSub;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description :
 * @Date : 2022-03-27
 */
public class RedisMsgPubSubListener extends JedisPubSub {
    private Logger logger = LoggerFactory.getLogger(RedisMsgPubSubListener.class);

    @Override
    public void unsubscribe() {
        super.unsubscribe();
    }

    @Override
    public void unsubscribe(String... channels) {
        super.unsubscribe(channels);
    }

    @Override
    public void subscribe(String... channels) {
        super.subscribe(channels);
    }

    @Override
    public void psubscribe(String... patterns) {
        super.psubscribe(patterns);
    }

    @Override
    public void punsubscribe() {
        super.punsubscribe();
    }

    @Override
    public void punsubscribe(String... patterns) {
        super.punsubscribe(patterns);
    }

    @Override
    public void onMessage(String channel, String message) {
        logger.info("onMessage: channel[{}], message[{}]",channel, message);
        if("handlerOrder".equals(channel)) {
            String methodName = message.substring(message.lastIndexOf(".") + 1);
            String className = message.substring(0, message.lastIndexOf("."));
            try {
                Class aClass = Class.forName(className);
                Object object = aClass.newInstance();
                Method method = aClass.getDeclaredMethod(methodName);
                String str = String.valueOf(method.invoke(object));
                logger.error(str);
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPMessage(String pattern, String channel, String message) {
        logger.info("onPMessage: pattern[{}], channel[{}], message[{}]", pattern, channel, message);
    }

    @Override
    public void onSubscribe(String channel, int subscribedChannels) {
        logger.info("onSubscribe: channel[{}], subscribedChannels[{}]", channel, subscribedChannels);
    }

    @Override
    public void onPUnsubscribe(String pattern, int subscribedChannels) {
        logger.info("onPUnsubscribe: pattern[{}], subscribedChannels[{}]", pattern, subscribedChannels);
    }

    @Override
    public void onPSubscribe(String pattern, int subscribedChannels) {
        logger.info("onPSubscribe: pattern[{}], subscribedChannels[{}]", pattern, subscribedChannels);
    }

    @Override
    public void onUnsubscribe(String channel, int subscribedChannels) {
        logger.info("channel:{} is been subscribed:{}", channel, subscribedChannels);
    }
}
