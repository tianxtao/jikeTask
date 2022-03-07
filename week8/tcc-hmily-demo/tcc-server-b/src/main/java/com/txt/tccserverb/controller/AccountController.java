package com.txt.tccserverb.controller;

import com.txt.tccserverb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * date       : 2022/3/7
 * description: xxx
 */
@RestController
@RequestMapping("/bankA")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/transfer")
    public boolean transfer(@RequestParam("amount") Double amount, @RequestParam("fromAccount") String fromAccount,
                            @RequestParam("toAccount") String toAccount) {
        accountService.transfer(fromAccount, amount, toAccount);
        return true;
    }

    @RequestMapping("/receive")
    public boolean receive(@RequestParam("amount") Double amount, @RequestParam("accountNo") String accountNo) {
        accountService.addAccountBalance(accountNo, amount);
        return true;
    }
}
