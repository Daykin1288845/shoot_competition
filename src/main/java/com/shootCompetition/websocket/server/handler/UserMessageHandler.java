package com.shootCompetition.websocket.server.handler;

import com.shootCompetition.websocket.server.group.WSServerGroup;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * WebSocket服务端消息处理器
 * </p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022-09-18 14:48
 */
@Slf4j
public class UserMessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        String receiveText = msg.text();
        log.info("收到来自用户的消息：" + receiveText);
        WSServerGroup.sendToDeviceGroup(msg.text());
    }

}
