package com.txt.banktransferbank1.service.imp;

import com.txt.banktransferap.api.Bank1Service;
import com.txt.banktransferbank1.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description :
 * @Date : 2022-03-13
 */
@DubboService(version = "1.0.0", tag = "red", weight = 100)
public class Bank1ServiceImpl implements Bank1Service {
    @Autowired
    AccountService accountService;

    @Override
    public boolean transfer(Double amount, String fromAccount, String toAccount, int accountType) {
        accountService.transfer(fromAccount, amount, toAccount, accountType);
        return true;
    }

    @Override
    public boolean receive(Double amount, String accountNo, int accountType) {
        accountService.addAccountBalance(accountNo, amount, accountType);
        return false;
    }
}
