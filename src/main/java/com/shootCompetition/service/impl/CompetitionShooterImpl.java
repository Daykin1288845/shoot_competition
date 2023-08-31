package com.shootCompetition.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shootCompetition.common.enums.ResultEnum;
import com.shootCompetition.common.exception.DAOException;
import com.shootCompetition.common.exception.ResultException;
import com.shootCompetition.domain.dto.CompetitionShooterDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.dto.ShooterDTO;
import com.shootCompetition.domain.entity.CompetitionShooter;
import com.shootCompetition.domain.entity.Shooter;
import com.shootCompetition.domain.vo.CompetitionShooterVO;
import com.shootCompetition.domain.vo.ShooterVO;
import com.shootCompetition.mapper.CompetitionShooterMapper;
import com.shootCompetition.mapper.ShooterMapper;
import com.shootCompetition.service.CompetitionShooterService;
import com.shootCompetition.service.ShooterService;
import com.shootCompetition.service.impl.constant.MapperConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 接口implements刚写的ShooterService，然后alt+enter生成，自己写容易报错
 */
@Slf4j
@Service
public  class CompetitionShooterImpl implements CompetitionShooterService {

    @Resource
    private CompetitionShooterMapper competitionShooterMapper;

    @Override
    public PageInfo<CompetitionShooterVO> page(PageDTO pageDTO, CompetitionShooterDTO competitionShooterDTO){
        try{
            PageHelper.startPage(pageDTO);
            List<CompetitionShooterVO> List = competitionShooterMapper.list(competitionShooterDTO);
            return new PageInfo<>(List);
        } catch (Exception e){
            log.error("PAGE [shooter FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionShooterDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"查询选手分页列表失败");
        }
    }

    @Override
    public List<CompetitionShooterVO> list(CompetitionShooterDTO competitionShooterDTO) {
        try{
            return competitionShooterMapper.list(competitionShooterDTO);
        } catch (Exception e) {
            log.error("LIST [shooter] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionShooterDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"查询选手失败");
        }
    }

    @Override
    public int add(CompetitionShooterDTO competitionShooterDTO) {
        CompetitionShooter competitionShooter = new CompetitionShooter();
        BeanUtils.copyProperties(competitionShooterDTO,competitionShooter);

        int status;
        try{
            status = competitionShooterMapper.add(competitionShooter);
        } catch (DuplicateKeyException e){
            log.warn("ADD [shooter] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionShooterDTO,e.toString());
            throw new ResultException(ResultEnum.DUPLICATE_KEY_CONFLICT,"添加选手失败\n原因：选手id已存在");
        }catch (Exception e) {
            log.error("ADD [shooter] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionShooterDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "添加选手失败");
        }
        return status;
    }

    @Override
    public int update(CompetitionShooterDTO competitionShooterDTO) {
        CompetitionShooter competitionShooter = new CompetitionShooter();
        BeanUtils.copyProperties(competitionShooterDTO,competitionShooter);

        int status;
        try{
            status = competitionShooterMapper.update(competitionShooter);
            if (status == MapperConst.OBJECT_NULL){
                throw new DAOException("this [shooter not exist]");
            }
        } catch (DAOException e){
            log.warn("UPDATE [shooter] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionShooterDTO,e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL,"更新选手失败\n原因：选手不存在");
        } catch (Exception e){
            log.error("UPDATE [shooter] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionShooterDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"更新选手失败");
        }
        return status;
    }

    @Override
    public int delete(String id) {
        int status;
        try {
            status = competitionShooterMapper.delete(id);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [shooter not exist]");
            }
        } catch (DAOException e) {
            log.warn("DELETE [shooter] FAIL\nINPUT OBJECT: {}\nREASON: {}]", id, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "删除选手失败\n原因：选手不存在");
        } catch (Exception e) {
            log.error("DELETE [shooter] FAIL\nINPUT OBJECT: {}\nREASON: {}]", id, e.toString());
            throw new ResultException(ResultEnum.ERROR, "删除选手失败");
        }
        return status;
    }
}

