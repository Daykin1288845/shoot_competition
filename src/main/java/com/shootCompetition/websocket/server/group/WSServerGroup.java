package com.shootCompetition.websocket.server.group;


import com.shootCompetition.websocket.server.WebSocketServer;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.shootCompetition.websocket.server.group.WSChannelGroup.*;


/**
 * <p>
 *     websocket服务实例组
 * </p>
 * @author hqully
 * @version 1.0
 * @date 2023-02-22 17:07
 */
public class WSServerGroup {
    public static final String FRONT = "front";
    public static final String DEVICE = "device";
    public static final String USER = "user";

    public static Map<String, WebSocketServer> serverGroup = new ConcurrentHashMap<>();

    public static WebSocketServer getDeviceServer() {
        return serverGroup.get(DEVICE);
    }

    public static WebSocketServer getFrontServer() {
        return serverGroup.get(FRONT);
    }


    public static void sendToUserGroup(String msg) {
        for (String key : userChannelGroup.keySet()) {
            userChannelGroup.get(key).writeAndFlush(new TextWebSocketFrame(msg));
        }
    }

    public static void sendToFrontGroup(String msg) {
        for (String key : frontChannelGroup.keySet()) {
            frontChannelGroup.get(key).writeAndFlush(new TextWebSocketFrame(msg));
        }
    }

    public static void sendToDeviceGroup(String msg) {
        for (String key : deviceChannelGroup.keySet()) {
            deviceChannelGroup.get(key).writeAndFlush(new TextWebSocketFrame(msg));
        }
    }
}
