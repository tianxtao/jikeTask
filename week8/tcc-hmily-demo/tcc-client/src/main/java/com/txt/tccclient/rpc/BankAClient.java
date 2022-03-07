package com.txt.tccclient.rpc;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * date       : 2022/3/7
 * description: xxx
 */
@FeignClient(value="tcc-server-a",fallback=BankAClientFallback.class)
public interface BankAClient {
    //远程调用bankA的微服务
    @GetMapping("/bankA/transfer")
    @Hmily
    public boolean transfer(@RequestParam("amount") Double amount, @RequestParam("fromAccount") String fromAccount,
                           @RequestParam("toAccount") String toAccount);
}
