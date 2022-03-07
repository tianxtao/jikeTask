package com.txt.tccclient.rpc;

import org.springframework.stereotype.Component;

/**
 * date       : 2022/3/7
 * description: xxx
 */
@Component
public class BankBClientFallback implements BankBClient{
    @Override
    public boolean transfer(Double amount, String fromAccount, String toAccount) {
        return true;
    }
}
