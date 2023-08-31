package com.shootCompetition.controller;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CompetitionShooterDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.dto.ShooterDTO;
import com.shootCompetition.domain.vo.CompetitionShooterVO;
import com.shootCompetition.domain.vo.ResultVO;
import com.shootCompetition.domain.vo.ShooterVO;
import com.shootCompetition.service.CompetitionShooterService;
import com.shootCompetition.service.ShooterService;
import com.shootCompetition.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"赛程进行时选手数据接口"})
@RestController
@RequestMapping("/api/competitionShooter")
public class CompetitionShooterController {
    @Resource
    private CompetitionShooterService competitionShooterService;

    @ApiOperation(value = "分页查询选手")
    @GetMapping("/page")
    public ResultVO<PageInfo<CompetitionShooterVO>> page(PageDTO pageDTO, CompetitionShooterDTO competitionShooterDTO){
        PageInfo<CompetitionShooterVO> page = competitionShooterService.page(pageDTO,competitionShooterDTO);
        return ResultVOUtil.success(page);
    }

    @ApiOperation(value = "查询选手基本信息")
    @GetMapping("/list")
    public ResultVO<List<CompetitionShooterVO>> list(CompetitionShooterDTO competitionShooterDTO){
        List<CompetitionShooterVO> list = competitionShooterService.list(competitionShooterDTO);
        return ResultVOUtil.success(list);
    }

    @ApiOperation(value = "添加选手基本信息")
    @PostMapping("/add")
    public ResultVO<Integer> add(@RequestBody CompetitionShooterDTO competitionShooterDTO){
        competitionShooterService.add(competitionShooterDTO);
        return ResultVOUtil.success("添加选手基本信息成功！");
    }

    @ApiOperation(value = "更新选手当前得分信息")
    @PostMapping("/update")
    public ResultVO<Integer> update(@RequestBody CompetitionShooterDTO competitionShooterDTO){
        competitionShooterService.update(competitionShooterDTO);
        return ResultVOUtil.success("更新选手基本信息成功");
    }

    @ApiOperation(value = "删除基本信息")
    @DeleteMapping("/delete/{shooterId}")
    public ResultVO<Integer> delete(@PathVariable("shooterId") String id){
        competitionShooterService.delete(id);
        return ResultVOUtil.success("删除选手基本信息成功");
    }
}