package com.txt.banktransferap.api;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description :
 * @Date : 2022-03-13
 */
public interface Bank2Service {
    @Hmily
    boolean transfer(Double amount, String fromAccount, String toAccount, int accountType);

    // receive一般和transfer同时发生，故这里不再走新事务
    boolean receive(Double amount, String accountNo, int accountType);
}
