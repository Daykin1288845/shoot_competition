package com.shootCompetition.websocket.server.handler;


import com.shootCompetition.websocket.server.group.WSChannelGroup;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 前端Channel连接的处理器
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022-09-18 11:13
 */
@Slf4j
public class FrontConnectHandler extends ChannelInboundHandlerAdapter {


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info(ctx.name() + " : server: {} connect with front: {}", channel.localAddress(), channel.remoteAddress());
        WSChannelGroup.frontChannelGroup.put(channel.id().toString(), channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        log.info(ctx.name() + " : server: {} disconnect form client: {}", channel.localAddress(), channel.remoteAddress());
        WSChannelGroup.frontChannelGroup.remove(channel.id().toString(), channel);
    }

}
