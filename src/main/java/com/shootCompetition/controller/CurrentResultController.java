package com.shootCompetition.controller;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.CurrentResultDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.domain.vo.CurrentResultVO;
import com.shootCompetition.domain.vo.ResultVO;
import com.shootCompetition.service.CurrentResultService;
import com.shootCompetition.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"选手当前得分数据接口"})
@RestController
@RequestMapping("/api/current-result")
public class CurrentResultController {
    @Resource
    private CurrentResultService currentResultService;

    @ApiOperation(value = "分页查询比赛记录")
    @GetMapping("/page")
    public ResultVO<PageInfo<CurrentResultVO>> page(PageDTO pageDTO, CurrentResultDTO currentResultDTO){
        PageInfo<CurrentResultVO> page = currentResultService.page(pageDTO,currentResultDTO);
        return ResultVOUtil.success(page);
    }

    @ApiOperation(value = "查询选手当前得分")
    @GetMapping("/list")
    public ResultVO<List<CurrentResultVO>> list(CurrentResultDTO currentResultDTO){
        List<CurrentResultVO> list = currentResultService.list(currentResultDTO);
        return ResultVOUtil.success(list);
    }

    @ApiOperation(value = "新增选手当前得分信息")
    @PostMapping("/add")
    public ResultVO<Integer> add(@RequestBody CurrentResultDTO currentResultDTO){
        currentResultService.add(currentResultDTO);
        return ResultVOUtil.success("添加选手当前得分信息成功！");
    }

    @ApiOperation(value = "更新选手当前得分信息")
    @PostMapping("/update")
    public ResultVO<Integer> update(@RequestBody CurrentResultDTO currentResultDTO){
        currentResultService.update(currentResultDTO);
        return ResultVOUtil.success("更新选手当前得分信息成功");
    }

    @ApiOperation(value = "删除选手当前得分信息")
    @DeleteMapping("/delete/{id}")
    public ResultVO<Integer> delete(@PathVariable("id") Integer id){
        currentResultService.delete(id);
        return ResultVOUtil.success("删除选手当前得分信息成功");
    }
}
