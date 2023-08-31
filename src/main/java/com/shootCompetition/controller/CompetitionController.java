package com.shootCompetition.controller;

import com.shootCompetition.common.global.GlobalVariables;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.vo.ResultVO;
import com.shootCompetition.service.CompetitionRecordService;
import com.shootCompetition.utils.ResultVOUtil;
import com.shootCompetition.websocket.server.group.WSChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;


@Api(tags = "开启/结束比赛")
@RestController
@RequestMapping("/api/competition")
public class CompetitionController {


    @Resource
    private CompetitionRecordService competitionRecordService;

    /**
     * 组播方法
     * @param receiveText
     */
    private void sendToGroup(String receiveText) {
        for (String key : WSChannelGroup.deviceChannelGroup.keySet()) {
            WSChannelGroup.deviceChannelGroup.get(key).writeAndFlush(new TextWebSocketFrame(receiveText));
        }
    }

    /**
     * 其实就是添加比赛记录
     * @param competitionRecordDTO
     * @return
     */
    @ApiOperation("添加比赛")
    @PostMapping("/add")
    public ResultVO<Integer> addCompetition(@RequestBody CompetitionRecordDTO competitionRecordDTO){
        competitionRecordService.add(competitionRecordDTO);
        GlobalVariables.currentCompetitionId=competitionRecordDTO.getId().intValue();
        return ResultVOUtil.success("添加比赛成功!");
    }

    /**
     * 根据id，让已添加的比赛正式开始
     * 但是要判断该比赛是否早就开始，或者是否有该比赛存在
     * @param id
     * @return
     */


    @ApiOperation("开始比赛")
    @GetMapping("/start")
    public ResultVO<Integer> start(@RequestParam("id") int id){
        if(competitionRecordService.get(id)!=null){
            if(competitionRecordService.get(id).getStartTime()!=null){
                 return ResultVOUtil.success("比赛已开始,无法重复开始");
            }
            else {
                CompetitionRecordDTO competitionRecordDTO = new CompetitionRecordDTO();
                BeanUtils.copyProperties(competitionRecordService.get(id),competitionRecordDTO);
                competitionRecordDTO.setStartTime(LocalDateTime.now());
                competitionRecordService.update(competitionRecordDTO);
                sendToGroup("{\n" +
                        "    \"type\": \"notification\",\n" +
                        "    \"data\": \"compStart\"\n" +
                        "}");


                return ResultVOUtil.success("比赛成功开始");
            }
        }
        else {
            return ResultVOUtil.error("没有比赛记录");
        }
    }

    /**
     * 大致和开始比赛相同，只不过判断是否有结束时间存在
     * @param id
     * @return
     */
    @ApiOperation("结束比赛")
    @GetMapping("/end")
    public ResultVO<Integer> end(@RequestParam("id") int id){
        if(competitionRecordService.get(id)!=null){
            if (competitionRecordService.get(id).getEndTime()!=null){
                return ResultVOUtil.success("比赛已结束,无法重复结束");
            }
            else {
                CompetitionRecordDTO competitionRecordDTO = new CompetitionRecordDTO();
                BeanUtils.copyProperties(competitionRecordService.get(id),competitionRecordDTO);
                competitionRecordDTO.setEndTime(LocalDateTime.now());
                competitionRecordService.update(competitionRecordDTO);
                sendToGroup("{\n" +
                        "    \"type\": \"notification\",\n" +
                        "    \"data\": \"compEnd\"\n" +
                        "}");
                return ResultVOUtil.success("比赛顺利结束");
            }
        }
        else {
            return ResultVOUtil.success("没有比赛记录");
        }
    }
}
