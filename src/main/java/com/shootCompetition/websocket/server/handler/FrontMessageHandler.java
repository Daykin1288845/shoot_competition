package com.shootCompetition.websocket.server.handler;

import com.shootCompetition.domain.vo.ShootRankSummaryVO;
import com.shootCompetition.domain.vo.ShootRankVO;
import com.shootCompetition.utils.JsonUtil;
import com.shootCompetition.websocket.server.group.WSChannelGroup;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * <p>
 * WebSocket前端消息处理器
 * </p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022-09-18 14:48
 */
@Slf4j
public class FrontMessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        /**
         * 判断前端是否输入test，若是则调用channel的writeAndFlush，回信
         * 将包装的mockData的java对象转换成json给前端
         */
       if( "test".equals(msg.text())){
           ctx.channel().writeAndFlush(new TextWebSocketFrame("111"));
//           ctx.channel().writeAndFlush(new TextWebSocketFrame(JsonUtil.toJson(mockData())));
            sendToGroup("123");
       }





    }

    private void sendToGroup(String receiveText) {
        for (String key : WSChannelGroup.deviceChannelGroup.keySet()) {
            WSChannelGroup.deviceChannelGroup.get(key).writeAndFlush(new TextWebSocketFrame(receiveText));
        }
    }


//    private ShootRankSummaryVO mockData(){
//        ShootRankVO vo1 = new ShootRankVO();
//        ShootRankVO vo2 = new ShootRankVO();
//
//        vo1.setName("a");
//        vo1.setRing(6);
//        vo1.setScore(40);
//        vo1.setSequence(54);
//        vo1.setShooterId(1);
//
//        vo2.setName("b");
//        vo2.setRing(61);
//        vo2.setScore(401);
//        vo2.setSequence(541);
//        vo2.setShooterId(11);
//
//        ArrayList<ShootRankVO> vos = new ArrayList<>();
//        vos.add(vo1);
//        vos.add(vo2);
//
//        ShootRankSummaryVO sum = new ShootRankSummaryVO();
//        sum.setList(vos);
//        return sum;
//    }



}
