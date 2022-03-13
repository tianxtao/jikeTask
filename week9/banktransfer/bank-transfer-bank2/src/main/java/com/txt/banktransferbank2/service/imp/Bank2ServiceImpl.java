package com.txt.banktransferbank2.service.imp;

import com.txt.banktransferap.api.Bank2Service;
import com.txt.banktransferbank2.service.AccountService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description :
 * @Date : 2022-03-13
 */
@DubboService(version = "1.0.0", tag = "red", weight = 100)
public class Bank2ServiceImpl implements Bank2Service {
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
