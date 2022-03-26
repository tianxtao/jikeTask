package com.txt.redisdemo.service;

import org.springframework.stereotype.Service;

/**
 * @Description :
 * @Date : 2022-03-27
 */
@Service
public class OrderService {
    public String handlerOrder() {
        return "开始处理订单...";
    }
}
