package com.shootCompetition.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shootCompetition.common.enums.ResultEnum;
import com.shootCompetition.common.exception.DAOException;
import com.shootCompetition.common.exception.ResultException;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.entity.CompetitionRecord;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.mapper.CompetitionRecordMapper;
import com.shootCompetition.service.CompetitionRecordService;
import com.shootCompetition.service.impl.constant.MapperConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class CompetitionRecordServiceImpl implements CompetitionRecordService {

    @Resource
    private CompetitionRecordMapper competitionRecordMapper;

    @Override
    public PageInfo<CompetitionRecordVO> page(PageDTO pageDTO,CompetitionRecordDTO competitionRecordDTO){
        try{
            PageHelper.startPage(pageDTO);
            List<CompetitionRecordVO> List = competitionRecordMapper.list(competitionRecordDTO);
            return new PageInfo<>(List);
        } catch (Exception e){
            log.error("PAGE [competition_record FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionRecordDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"查询比赛记录分页列表失败");
        }
    }

    @Override
    public List<CompetitionRecordVO> list(CompetitionRecordDTO competitionRecordDTO) {
        try{
            return competitionRecordMapper.list(competitionRecordDTO);
        } catch (Exception e) {
            log.error("LIST [competitionRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionRecordDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"查询比赛记录失败");
        }
    }

    @Override
    public int add(CompetitionRecordDTO competitionRecordDTO) {
        CompetitionRecord competitionRecord = new CompetitionRecord();
        BeanUtils.copyProperties(competitionRecordDTO,competitionRecord);

        int status;
        try{
            status = competitionRecordMapper.add(competitionRecord);
        } catch (DuplicateKeyException e){
            log.warn("ADD [competitionRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionRecordDTO,e.toString());
            throw new ResultException(ResultEnum.DUPLICATE_KEY_CONFLICT,"添加比赛记录失败\n原因：比赛记录id已存在");
        }catch (Exception e) {
            log.error("ADD [competitionRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionRecordDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "添加比赛记录失败");
        }
        return status;
    }

    @Override
    public int update(CompetitionRecordDTO competitionRecordDTO) {
        CompetitionRecord competitionRecord = new CompetitionRecord();
        BeanUtils.copyProperties(competitionRecordDTO,competitionRecord);

        int status;
        try{
            status = competitionRecordMapper.update(competitionRecord);
            if (status == MapperConst.OBJECT_NULL){
                throw new DAOException("this [competition Record not exist]");
            }
        } catch (DAOException e){
            log.warn("UPDATE [competitionRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionRecordDTO,e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL,"更新比赛记录失败\n原因：比赛记录不存在");
        } catch (Exception e){
            log.error("UPDATE [competitionRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}]", competitionRecordDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"更新比赛记录失败");
        }
        return status;
    }

    @Override
    public int delete(Integer id) {
        int status;
        try {
            status = competitionRecordMapper.delete(id);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [competition Record not exist]");
            }
        } catch (DAOException e) {
            log.warn("DELETE [competitionRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}]", id, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "删除比赛记录失败\n原因：比赛记录不存在");
        } catch (Exception e) {
            log.error("DELETE [competitionRecord] FAIL\nINPUT OBJECT: {}\nREASON: {}]", id, e.toString());
            throw new ResultException(ResultEnum.ERROR, "删除比赛记录失败");
        }
        return status;
    }

    @Override
    public CompetitionRecordVO get(Integer id) {
        return competitionRecordMapper.get(id);
    }
}