package com.shootCompetition.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shootCompetition.common.enums.ResultEnum;
import com.shootCompetition.common.exception.DAOException;
import com.shootCompetition.common.exception.ResultException;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.CurrentResultDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.entity.CurrentResult;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.domain.vo.CurrentResultVO;
import com.shootCompetition.mapper.CurrentResultMapper;
import com.shootCompetition.service.CurrentResultService;
import com.shootCompetition.service.impl.constant.MapperConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class CurrentResultServiceImpl implements CurrentResultService {

    @Resource
    private CurrentResultMapper currentResultMapper;

    @Override
    public PageInfo<CurrentResultVO> page(PageDTO pageDTO, CurrentResultDTO currentResultDTO){
        try{
            PageHelper.startPage(pageDTO);
            List<CurrentResultVO> List = currentResultMapper.list(currentResultDTO);
            return new PageInfo<>(List);
        } catch (Exception e){
            log.error("PAGE [current_result FAIL\nINPUT OBJECT: {}\nREASON: {}]", currentResultDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"查询当前得分分页列表失败");
        }
    }

    @Override
    public List<CurrentResultVO> list(CurrentResultDTO currentResultDTO) {
        try{
            return currentResultMapper.list(currentResultDTO);
        } catch (Exception e) {
            log.error("LIST [currentResult] FAIL\nINPUT OBJECT: {}\nREASON: {}]", currentResultDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"查询当前得分失败");
        }
    }

    @Override
    public int add(CurrentResultDTO currentResultDTO) {
        CurrentResult currentResult = new CurrentResult();
        BeanUtils.copyProperties(currentResultDTO, currentResult);

        int status;
        try{
            status = currentResultMapper.add(currentResult);
        } catch (DuplicateKeyException e){
            log.warn("ADD [currentResult] FAIL\nINPUT OBJECT: {}\nREASON: {}]", currentResultDTO,e.toString());
            throw new ResultException(ResultEnum.DUPLICATE_KEY_CONFLICT,"添加当前得分失败\n原因：当前得分id已存在");
        }catch (Exception e) {
            log.error("ADD [currentResult] FAIL\nINPUT OBJECT: {}\nREASON: {}]", currentResultDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "添加当前得分失败");
        }
        return status;
    }

    @Override
    public int update(CurrentResultDTO currentResultDTO) {
        CurrentResult currentResult = new CurrentResult();
        BeanUtils.copyProperties(currentResultDTO, currentResult);

        int status;
        try{
            status = currentResultMapper.update(currentResult);
            if (status == MapperConst.OBJECT_NULL){
                throw new DAOException("this [current Result not exist]");
            }
        } catch (DAOException e){
            log.warn("UPDATE [currentResult] FAIL\nINPUT OBJECT: {}\nREASON: {}]", currentResultDTO,e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL,"更新当前得分失败\n原因：当前得分不存在");
        } catch (Exception e){
            log.error("UPDATE [currentResult] FAIL\nINPUT OBJECT: {}\nREASON: {}]", currentResultDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"更新当前得分失败");
        }
        return status;
    }

    @Override
    public int delete(Integer id) {
        int status;
        try {
            status = currentResultMapper.delete(id);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [current result not exist]");
            }
        } catch (DAOException e) {
            log.warn("DELETE [currentResult] FAIL\nINPUT OBJECT: {}\nREASON: {}]", id, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "删除当前得分失败\n原因：当前得分不存在");
        } catch (Exception e) {
            log.error("DELETE [currentResult] FAIL\nINPUT OBJECT: {}\nREASON: {}]", id, e.toString());
            throw new ResultException(ResultEnum.ERROR, "删除当前得分失败");
        }
        return status;
    }
}
