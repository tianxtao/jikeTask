package com.txt.rpcfxdemonetty;

import com.txt.rpcfxdemonetty.service.NettyHttpServer;
import com.txt.rpcfxdemonetty.service.RPCServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RpcfxDemoNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcfxDemoNettyApplication.class, args);
        new RPCServer(8085).start();
    }

}
