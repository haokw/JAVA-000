package io.gateway.outbound;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;

import java.net.URI;
import java.net.URL;


public class HttpClient {

    public void connect(String proxyServer, FullHttpRequest fullHttpRequest) throws Exception {
        URL u = new URL(proxyServer);
        String host = u.getHost();
        int port = u.getPort();
        String path = u.getPath();

        System.out.println("client request host:" + host);
        System.out.println("client request port:" + port);

        // 配置客户端 NIO 线程组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
            .option(ChannelOption.SO_KEEPALIVE, true)
            .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new HttpOutboundHandler());
                }
            });

            // 发起一步链接操作
            ChannelFuture f = b.connect(host, port).sync();

//            URI uri = new URI(path);
//            DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, uri.toASCIIString());
//
//            // 构建 http 请求
//            request.headers().set(HttpHeaders.Names.HOST, host);
//            request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
//            request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());

            // 发送请求
            f.channel().write(fullHttpRequest);
            f.channel().flush();

            // 等待客户端链路关闭
            f.channel().closeFuture().sync();
        } finally {
            // 优雅退出，释放 NIO 线程组
            group.shutdownGracefully();
        }
    }
}
