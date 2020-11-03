package io.gateway.inbound;

import io.gateway.filter.HttpRequestFilter;
import io.gateway.outbound.HttpClient;
import io.gateway.router.HttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.netty.handler.codec.http.HttpHeaderNames.*;
import static io.netty.handler.codec.http.HttpHeaderValues.*;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;


public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(HttpInboundHandler.class);
    private final String proxyServer;

    public HttpInboundHandler(String proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            // 获取请求
            HttpRequest request = (HttpRequest) msg;

            // filter
            HttpRequestFilter.filter(request, ctx);

            // router
            String url = HttpEndpointRouter.route(this.proxyServer);

            // 转发请求获取响应
            byte[] content = "not find server".getBytes();
            content = HttpClient.getResponse(url);

            // System.out.println("content: " + content);

            // 响应消息
            boolean keepAlive = HttpUtil.isKeepAlive(request);
            FullHttpResponse response = new DefaultFullHttpResponse(request.protocolVersion(), OK, Unpooled.wrappedBuffer(content));
            response.headers().set(CONTENT_TYPE, TEXT_PLAIN).setInt(CONTENT_LENGTH, response.content().readableBytes());

            if (keepAlive) {
                if (!request.protocolVersion().isKeepAliveDefault()) {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                }
            } else {
                response.headers().set(CONNECTION, CLOSE);
            }

            // 回复响应
            ctx.channel().writeAndFlush(response).addListeners(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()) {
                        ctx.channel().read();
                    } else {
                        channelFuture.channel().close();
                    }
                }
            });
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
     cause.printStackTrace();
     ctx.close();
    }
}
