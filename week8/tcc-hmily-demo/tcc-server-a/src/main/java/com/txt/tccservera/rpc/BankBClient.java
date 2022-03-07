package com.txt.tccservera.rpc;

import org.dromara.hmily.annotation.Hmily;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * date       : 2022/3/7
 * description: xxx
 */
@FeignClient(value="tcc-server-b",fallback=BankBClientFallback.class)
public interface BankBClient {
    //远程调用bankA的微服务
    @GetMapping("/bankB/receive")
    @Hmily
    public boolean receive(@RequestParam("amount") Double amount, @RequestParam("accountNo") String accountNo);
}
