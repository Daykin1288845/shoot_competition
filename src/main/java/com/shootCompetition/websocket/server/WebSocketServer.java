package com.shootCompetition.websocket.server;


import com.shootCompetition.websocket.server.initalizer.WSDeviceChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;


/**
 * <p>
 * websocket服务类
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022-10-06 08:39
 */
@Data
@Slf4j
public class WebSocketServer {

    private int port;

    private NioEventLoopGroup bossGroup;
    private NioEventLoopGroup workerGroup;
    private ChannelInitializer<SocketChannel> wsChannelInitializer = new WSDeviceChannelInitializer();
    private Channel channel;

    public WebSocketServer() {
    }

    public WebSocketServer(ChannelInitializer<SocketChannel> wsChannelInitializer,int port) {
        this.wsChannelInitializer = wsChannelInitializer;
        this.port = port;
        init();
    }

    public void init() {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap()
                    .group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(wsChannelInitializer);

            ChannelFuture f = serverBootstrap.bind(port).addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    log.info("websocket start successful at " + channel.localAddress());
                }
            });
            channel = f.channel();
            channel.closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    workerGroup.shutdownGracefully();
                    bossGroup.shutdownGracefully();
                }
            });
        } catch (Exception e) {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
            log.error("websocket start fail, cause: {}", e.toString());

        }
    }


    public void destroy() {
        if (null == channel) {
            return;
        }
        channel.close();
        log.info("webSocket server closed");
    }

    public void sendMessage(String msg, Channel channel) {
        channel.writeAndFlush(new TextWebSocketFrame(msg));
    }

}
