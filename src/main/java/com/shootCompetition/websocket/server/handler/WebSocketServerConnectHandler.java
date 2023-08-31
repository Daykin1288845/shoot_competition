package com.shootCompetition.websocket.server.handler;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;

/**
 * <p>
 * WebSocket服务端连接控制器
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022/9/20 20:13
 */
public class WebSocketServerConnectHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof FullHttpRequest) {
            /* http请求传到该处理器说明握手请求被丢弃,且原因为客户端请求的websocket路径错误,
             * 返回404并关闭channel.
             */
            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                                                                    HttpResponseStatus.NOT_FOUND);
            ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

}
