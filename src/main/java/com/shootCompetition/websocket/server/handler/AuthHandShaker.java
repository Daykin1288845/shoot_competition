package com.shootCompetition.websocket.server.handler;

import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketFrameDecoder;
import io.netty.handler.codec.http.websocketx.WebSocketFrameEncoder;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;

/**
 * <p>
 * websocket鉴权握手控制器
 * <p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022/11/1 21:38
 */
public class AuthHandShaker extends WebSocketServerHandshaker {

    protected AuthHandShaker(WebSocketVersion version, String uri, String subprotocols, int maxFramePayloadLength) {
        super(version, uri, subprotocols, maxFramePayloadLength);
    }


    @Override
    protected FullHttpResponse newHandshakeResponse(FullHttpRequest req, HttpHeaders responseHeaders) {
        String token = responseHeaders.get("Sec-WebSocket-Protocol");

        return null;
    }

    @Override
    protected WebSocketFrameDecoder newWebsocketDecoder() {
        return null;
    }

    @Override
    protected WebSocketFrameEncoder newWebSocketEncoder() {
        return null;
    }

}
