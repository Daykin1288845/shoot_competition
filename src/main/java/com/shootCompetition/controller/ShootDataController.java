package com.shootCompetition.controller;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.dto.ShootDataDTO;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.domain.vo.ResultVO;
import com.shootCompetition.domain.vo.ShootDataVO;
import com.shootCompetition.service.ShootDataService;
import com.shootCompetition.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

@Api(tags = "射击数据")
@RestController
@RequestMapping("/api/shoot-data")
public class ShootDataController {
    @Resource
    private ShootDataService shootDataService;

    @ApiOperation(value = "分页查询射击数据")
    @GetMapping("/page")
    public ResultVO<PageInfo<ShootDataVO>> page(PageDTO pageDTO, ShootDataDTO shootDataDTO){
        PageInfo<ShootDataVO> page = shootDataService.page(pageDTO,shootDataDTO);
        return ResultVOUtil.success(page);
    }

    @ApiOperation(value = "查询射击数据")
    @GetMapping("/list")
    public ResultVO<List<ShootDataVO>> list(ShootDataDTO shootDataDTO){
        List<ShootDataVO> list = shootDataService.list(shootDataDTO);
        return ResultVOUtil.success(list);
    }

    @ApiOperation(value = "新增射击数据")
    @PostMapping("/add")
    public ResultVO<Integer> add(@RequestBody ShootDataDTO shootDataDTO){
        shootDataService.add(shootDataDTO);
        return ResultVOUtil.success("添加射击数据成功！");
    }

    @ApiOperation(value = "更新射击数据")
    @PostMapping("/update")
    public ResultVO<Integer> update(@RequestBody ShootDataDTO shootDataDTO){
        shootDataService.update(shootDataDTO);
        return ResultVOUtil.success("更新射击数据成功！");
    }

    @ApiOperation(value = "删除射击数据")
    @DeleteMapping("/delete/{id}")
    public ResultVO<Integer> delete(@PathVariable("id") Integer id){
        shootDataService.delete(id);
        return ResultVOUtil.success("删除射击数据成功!");
    }

}
