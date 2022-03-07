package com.txt.tccserverb.rpc;

import org.springframework.stereotype.Component;

/**
 * date       : 2022/3/7
 * description: xxx
 */
@Component
public class BankAClientFallback implements BankAClient{

    @Override
    public boolean receive(Double amount, String accountNo) {
        return true;
    }
}
