package com.shootCompetition.websocket.server.group;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * <p>
 *     websocket设备和用户channel组
 * </p>
 * @author hqully
 * @version 1.0
 * @date 2023-02-22 17:07
 */
public class WSChannelGroup {
    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 和设备建立的 {@link Channel}组,
     * {@link Channel#id()}为键, {@link Channel}为值
     */
    public static Map<String, Channel> deviceChannelGroup = new ConcurrentHashMap<>();
    public static Map<Channel, String> channelDeviceGroup = new ConcurrentHashMap<>();

    /**
     * 和前端页面建立的 {@link Channel}组,
     * {@link Channel#id()}为键, {@link Channel}为值
     */
    public static Map<String, Channel> frontChannelGroup = new ConcurrentHashMap<>();
    public static Map<Channel, String> channelFrontGroup = new ConcurrentHashMap<>();

    /**
     * 和小程序用户页面建立的 {@link Channel}组,
     * {@link Channel#id()}为键, {@link Channel}为值
     */
    public static Map<String, Channel> userChannelGroup = new ConcurrentHashMap<>();
    public static Map<Channel, String> channelUserGroup = new ConcurrentHashMap<>();

    /**
     * 和LOG测试页面建立的 {@link Channel}组,
     * {@link Channel#id()}为键, {@link Channel}为值
     */
    public static Map<String, Channel> logChannelGroup = new ConcurrentHashMap<>();
    public static Map<Channel, String> channelLogGroup = new ConcurrentHashMap<>();
}
