package com.txt.filter;

import io.github.kimmking.gateway.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;

/**
 * @Description :
 * @Date : 2022-01-21
 */
public class ProxyBizFilter implements HttpRequestFilter {

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        String uri = fullRequest.uri();

        if(uri.startsWith("/whale")) {
        } else {
            throw new RuntimeException("无效uri:" + uri);
        }

        HttpHeaders headers = fullRequest.headers();
        if (headers == null) {
            headers = new DefaultHttpHeaders();
        }
        headers.add("proxy-tag", this.getClass().getSimpleName());
    }
}
