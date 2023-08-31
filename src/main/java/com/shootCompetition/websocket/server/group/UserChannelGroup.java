package com.shootCompetition.websocket.server.group;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserChannelGroup {
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    public static Map<String, Channel> deviceChannelGroup = new ConcurrentHashMap<>();
    public static Map<Channel, String> channelDeviceGroup = new ConcurrentHashMap<>();
}
