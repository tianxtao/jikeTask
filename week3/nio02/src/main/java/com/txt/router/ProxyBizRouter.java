package com.txt.router;

import io.github.kimmking.gateway.router.HttpEndpointRouter;

import java.util.List;
import java.util.Random;

/**
 * @Description :
 * @Date : 2022-01-21
 */
public class ProxyBizRouter implements HttpEndpointRouter{

    @Override
    public String route(List<String> endpoints) {
        return endpoints.get(new Random().nextInt(endpoints.size()));
    }
}
