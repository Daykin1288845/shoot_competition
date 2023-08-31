package com.shootCompetition.websocket.server.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * WebSocket服务端统一异常处理
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022/9/20 19:31
 */
@Slf4j
public class WebSocketServerExceptionHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.error(cause.toString());
        cause.printStackTrace();
        ctx.channel().close();
    }

}
