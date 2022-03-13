package com.txt.banktransferclient.controller;

import com.txt.banktransferap.api.Bank1Service;
import com.txt.banktransferap.api.Bank2Service;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description :
 * @Date : 2022-03-13
 */
@RestController("transfer")
public class TransferController {
    @DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
    private Bank1Service bank1Service;

    @DubboReference(version = "1.0.0") //, url = "dubbo://127.0.0.1:12345")
    private Bank2Service bank2Service;

    @GetMapping("/test")
    public void test() {
        //张三用5美元向李四兑换35人民币
        bank1Service.transfer(5.00, "123456789", "987654321", 2);
        bank2Service.transfer(35.00, "987654321", "123456789", 1);
    }
}
