package com.shootCompetition.websocket.server.handler;


import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shootCompetition.common.global.GlobalVariables;
import com.shootCompetition.domain.dto.CompetitionShooterDTO;
import com.shootCompetition.domain.dto.ShootDataDTO;
import com.shootCompetition.domain.dto.ShooterDTO;
import com.shootCompetition.domain.entity.ShootData;
import com.shootCompetition.domain.entity.Shooter;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.domain.vo.CompetitionShooterVO;
import com.shootCompetition.domain.vo.ShootRankSummaryVO;
import com.shootCompetition.domain.vo.ShootRankVO;
import com.shootCompetition.service.*;
import com.shootCompetition.utils.JsonUtil;
import com.shootCompetition.utils.ResultVOUtil;
import com.shootCompetition.utils.SpringContextUtil;
import com.shootCompetition.websocket.server.group.WSChannelGroup;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;


/**
 * <p>
 * 设备消息处理器
 * </p>
 *
 * @author hqully
 * @version 1.0
 * @date 2022-09-18 14:48
 */
@Slf4j
public class DeviceMessageHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private CompetitionRecordService competitionRecordService;
    private ShootDataService shootDataService;
    private CompetitionShooterService competitionShooterService;

    public DeviceMessageHandler() {
        competitionRecordService = SpringContextUtil.getBean(CompetitionRecordService.class);
        shootDataService = SpringContextUtil.getBean(ShootDataService.class);
        competitionShooterService = SpringContextUtil.getBean(CompetitionShooterService.class);

    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        /*
        先判断是否有比赛，并且比赛是否开始，才可插入比赛数据等等
         */
        String receiveText = msg.text();
        log.info("设备传来信息：" + receiveText);

        ObjectMapper objectMapper = new ObjectMapper();
                     /*
                     解决jackson全局忽略字段不匹配的问题,如果不忽略的话，当字段不匹配时报错：Unrecognized field ...
                      */
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

                     /*
                     使用jsonNode对象作为转换的通用对象
                     */
        JsonNode jsonNode = objectMapper.readTree(receiveText);
        CompetitionRecordVO competitionRecordVO = competitionRecordService.get(GlobalVariables.currentCompetitionId);
        if (competitionRecordVO != null) { //判断是否添加比赛
            if (jsonNode.path("type").asText().equals("shooterData")) { //有比赛之后添加选手信息，判断传入json数据type是否为shooterData
                CompetitionShooterDTO shooter = objectMapper.convertValue(jsonNode.path("data"), CompetitionShooterDTO.class); //json转object
                shooter.setRecordId((long) GlobalVariables.currentCompetitionId);//全局变量当前比赛的id传入添加的选手信息中
                competitionShooterService.add(shooter); //调用service的基本业务add，将选手信息添加至数据库
                ctx.channel().writeAndFlush(new TextWebSocketFrame("选手信息已更新至数据库"));
            } else if (competitionRecordVO.getStartTime() != null) { //然后判断比赛是否开始
                if (jsonNode.path("type").asText().equals("shootData")) { //比赛开始后接受设备传来的射击数据，判断json类型，然后下一步处理
                    ShootDataDTO data = objectMapper.convertValue(jsonNode.path("data"), ShootDataDTO.class);//json转object

                    CompetitionShooterDTO competitionShooterDTO = new CompetitionShooterDTO();
                    competitionShooterDTO.setRecordId((long) GlobalVariables.currentCompetitionId);
                    List<CompetitionShooterVO> list = competitionShooterService.list(competitionShooterDTO);

                    List<CompetitionShooterVO> collect = list.stream() //转化为流形式
                            .filter(shootRankVO -> shootRankVO.getShooterId().equals(data.getShooterId().toString())) //判断->之后的语句的Boolean值，返回->左边的参数
                            .collect(Collectors.toList()); // 上面判断true则收集shootRankVO进collect里
                    if (collect.size() == 0) {
                        return;
                    }

//                    for (int i = 0; i < GlobalVariables.SRS.getList().size(); i++) {
//                        if (data.getShooterId() != GlobalVariables.SRS.getList().get(i).getShooterId()) {
//                            return;
//                        }
//                    }
                    shootDataService.add(data); //将射击数据全部存入数据库
                    mockData(jsonNode.path("data")); //将json格式的data的指定字段提取合并为对象，存入全局变量的list中
                        /*
                        组播，将特定字段包装好 发送给前端
                         */
                    sendToGroup(JsonUtil.toJson(GlobalVariables.SRS));

                }
            }
        } else {
            ctx.channel().writeAndFlush(new TextWebSocketFrame("当前没有比赛或比赛暂未开始"));
        }


    }

    private void mockData(JsonNode data) {
        ShootRankVO vo1 = new ShootRankVO();
        dataToVo(data, vo1);
        for (int i = 0; i < GlobalVariables.SRS.getList().size(); i++) {
            if (GlobalVariables.SRS.getList().get(i).getShooterId() == data.path("shooterId").asInt()) {
                GlobalVariables.SRS.getList().remove(i);
            }
        }
        GlobalVariables.SRS.getList().add(vo1);
    }

    private void dataToVo(JsonNode data, ShootRankVO vo1) {
        vo1.setName(data.path("shooterName").asText());
        vo1.setRing(data.path("hitRingNumber").asDouble());
        vo1.setScore(data.path("score").asDouble());
        vo1.setSequence(data.path("sequence").asInt());
        vo1.setShooterId(data.path("shooterId").asInt());
        vo1.setTotalRing(data.path("totalRing").asDouble());
    }


    private void sendToGroup(String receiveText) {
        for (String key : WSChannelGroup.frontChannelGroup.keySet()) {
            WSChannelGroup.frontChannelGroup.get(key).writeAndFlush(new TextWebSocketFrame(receiveText));
        }
    }


/*
    String str = "{\n" +
            "    \"type\": \"shootData\",\n" +
            "    \"data\": {\n" +
            "        \"shooterId\": 1,\n" +
            "        \"shooterName\": \"name\",\n" +
            "        \"sequence\" : 1,\n" +
            "        \"shootTime\" : \"2023-08-09 10:55:42\",\n" +
            "        \"aimRingNumber\" : 3.6,\n" +
            "        \"hitRingNumber\" : 8.6,\n" +
            "        \"gunShaking\" : 21,\n" +
            "        \"gunShakingRate\" : 86,\n" +
            "        \"fireShakingRate\" : 24,\n" +
            "        \"fireShaking\" : 20,\n" +
            "        \"shootingAccuracy\" : 54.67,\n" +
            "        \"gunStability\" : 59.93,\n" +
            "        \"fireStability\" : 59.45,\n" +
            "        \"score\" : 57.97\n" +
            "    }\n" +
            "}";

    String str2="{\n" +
            "    \"type\": \"shooterData\",\n" +
            "    \"data\": {\n" +
            "        \"shooterId\": 1,\n" +
            "        \"shooterName\": \"name\"\n" +
            "    }\n" +
            "}";

 */
}
