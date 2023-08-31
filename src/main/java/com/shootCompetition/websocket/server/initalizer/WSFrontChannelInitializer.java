package com.shootCompetition.websocket.server.initalizer;


import com.shootCompetition.websocket.server.handler.FrontConnectHandler;
import com.shootCompetition.websocket.server.handler.FrontMessageHandler;
import com.shootCompetition.websocket.server.handler.WebSocketServerConnectHandler;
import com.shootCompetition.websocket.server.handler.WebSocketServerExceptionHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;


/**
 * <p>
 * WebSocket服务端初始化处理器
 * </p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022-09-21 19:43
 */
public class WSFrontChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        initHandles(ch);
    }

    private void initHandles(SocketChannel ch) {
        ch.pipeline().addLast("HttpServerCodec", new HttpServerCodec());
        ch.pipeline().addLast("HttpObjectAggregator", new HttpObjectAggregator(65536));
        ch.pipeline().addLast("ChunkedWriteHandler", new ChunkedWriteHandler());
        ch.pipeline().addLast("WebSocketServerProtocolHandler", new WebSocketServerProtocolHandler("/", null, true, 65536 * 10));
        ch.pipeline().addLast("FrontConnectHandler", new FrontConnectHandler());
        ch.pipeline().addLast("WebSocketServerConnectHandler", new WebSocketServerConnectHandler());
        ch.pipeline().addLast("FrontMessageHandler", new FrontMessageHandler());
        ch.pipeline().addLast("WebSocketServerExceptionHandler", new WebSocketServerExceptionHandler());
    }


}
