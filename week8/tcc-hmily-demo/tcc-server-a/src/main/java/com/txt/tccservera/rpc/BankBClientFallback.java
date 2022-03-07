package com.txt.tccservera.rpc;

import org.springframework.stereotype.Component;

/**
 * date       : 2022/3/7
 * description: xxx
 */
@Component
public class BankBClientFallback implements BankBClient{
    @Override
    public boolean receive(Double amount, String accountNo) {
        return true;
    }
}
