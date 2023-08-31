package com.shootCompetition.controller;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.CurrentResultDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.dto.ShooterDTO;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.domain.vo.CurrentResultVO;
import com.shootCompetition.domain.vo.ResultVO;
import com.shootCompetition.domain.vo.ShooterVO;
import com.shootCompetition.service.CurrentResultService;
import com.shootCompetition.service.ShooterService;
import com.shootCompetition.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = {"选手信息数据接口"})
@RestController
@RequestMapping("/api/shooter")
public class ShooterController {
    @Resource
    private ShooterService shooterService;

    @ApiOperation(value = "分页查询选手")
    @GetMapping("/page")
    public ResultVO<PageInfo<ShooterVO>> page(PageDTO pageDTO, ShooterDTO shooterDTO){
        PageInfo<ShooterVO> page = shooterService.page(pageDTO,shooterDTO);
        return ResultVOUtil.success(page);
    }

    @ApiOperation(value = "查询选手基本信息")
    @GetMapping("/list")
    public ResultVO<List<ShooterVO>> list(ShooterDTO shooterDTO){
        List<ShooterVO> list = shooterService.list(shooterDTO);
        return ResultVOUtil.success(list);
    }

    @ApiOperation(value = "添加选手基本信息")
    @PostMapping("/add")
    public ResultVO<Integer> add(@RequestBody ShooterDTO shooterDTO){
        shooterService.add(shooterDTO);
        return ResultVOUtil.success("添加选手基本信息成功！");
    }

    @ApiOperation(value = "更新选手当前得分信息")
    @PostMapping("/update")
    public ResultVO<Integer> update(@RequestBody ShooterDTO shooterDTO){
        shooterService.update(shooterDTO);
        return ResultVOUtil.success("更新选手基本信息成功");
    }

    @ApiOperation(value = "删除基本信息")
    @DeleteMapping("/delete/{id}")
    public ResultVO<Integer> delete(@PathVariable("id") Integer id){
        shooterService.delete(id);
        return ResultVOUtil.success("删除选手基本信息成功");
    }
}