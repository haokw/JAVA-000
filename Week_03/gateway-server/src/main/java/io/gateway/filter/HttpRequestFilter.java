package io.gateway.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;


public interface HttpRequestFilter {
    static public void filter(HttpRequest Request, ChannelHandlerContext ctx) {
        return;
    }
}
