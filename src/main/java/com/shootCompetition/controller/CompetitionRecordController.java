package com.shootCompetition.controller;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.domain.vo.ResultVO;
import com.shootCompetition.service.CompetitionRecordService;
import com.shootCompetition.utils.ResultVOUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"比赛记录数据接口"})
@RestController
@RequestMapping("/api/competition-record")
public class CompetitionRecordController {
    @Resource
    private CompetitionRecordService  competitionRecordService;

    @ApiOperation(value = "分页查询比赛记录")
    @GetMapping("/page")
    public ResultVO<PageInfo<CompetitionRecordVO>> page(PageDTO pageDTO,CompetitionRecordDTO competitionRecordDTO){
        PageInfo<CompetitionRecordVO> page = competitionRecordService.page(pageDTO,competitionRecordDTO);
        return ResultVOUtil.success(page);
    }

    @ApiOperation(value = "查询比赛记录列表")
    @GetMapping("/list")
    public ResultVO<List<CompetitionRecordVO>> list(CompetitionRecordDTO competitionRecordDTO) {
        List<CompetitionRecordVO> list = competitionRecordService.list(competitionRecordDTO);
        return ResultVOUtil.success(list);
    }

    @ApiOperation(value = "新增比赛记录")
    @PostMapping("/add")
   public ResultVO<Integer> add(@RequestBody CompetitionRecordDTO competitionRecordDTO){
        competitionRecordService.add(competitionRecordDTO);
        return ResultVOUtil.success("添加比赛记录成功!");
   }
    @ApiOperation(value = "更新比赛记录")
    @PostMapping("/update")
   public ResultVO<Integer> update(@RequestBody CompetitionRecordDTO competitionRecordDTO){
        competitionRecordService.update(competitionRecordDTO);
        return ResultVOUtil.success("更新比赛记录成功!");
   }

   @ApiOperation(value = "删除比赛记录")
   @DeleteMapping("/delete/{id}")
   public ResultVO<Integer> delete(@PathVariable("id") Integer id){
        competitionRecordService.delete(id);
        return ResultVOUtil.success("删除比赛记录成功!");
   }
}
