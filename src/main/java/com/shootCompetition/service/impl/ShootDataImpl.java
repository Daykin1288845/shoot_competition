package com.shootCompetition.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.shootCompetition.common.enums.ResultEnum;
import com.shootCompetition.common.exception.DAOException;
import com.shootCompetition.common.exception.ResultException;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.dto.ShootDataDTO;
import com.shootCompetition.domain.entity.ShootData;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.domain.vo.ShootDataVO;
import com.shootCompetition.mapper.ShootDataMapper;
import com.shootCompetition.service.ShootDataService;
import com.shootCompetition.service.impl.constant.MapperConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service
public class ShootDataImpl implements ShootDataService {

    @Resource
    private ShootDataMapper shootDataMapper;

    @Override
    public PageInfo<ShootDataVO> page(PageDTO pageDTO, ShootDataDTO shootDataDTO){
        try{
            PageHelper.startPage(pageDTO);
            List<ShootDataVO> List = shootDataMapper.list(shootDataDTO);
            return new PageInfo<>(List);
        } catch (Exception e){
            log.error("PAGE [shootData] FAIL\nINPUT OBJECT: {}\nREASON: {}]", shootDataDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"查询射击数据分页列表失败");
        }
    }

    @Override
    public List<ShootDataVO> list(ShootDataDTO shootDataDTO) {
        try{
            return shootDataMapper.list(shootDataDTO);
        } catch (Exception e) {
            log.error("LIST [shootData] FAIL\nINPUT OBJECT: {}\nREASON: {}]", shootDataDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"查询射击数据失败");
        }
    }

    @Override
    public int add(ShootDataDTO shootDataDTO) {
        ShootData shootData = new ShootData();
        BeanUtils.copyProperties(shootDataDTO,shootData);

        int status;
        try{
            status = shootDataMapper.add(shootData);
        } catch (DuplicateKeyException e){
            log.warn("ADD [shootData] FAIL\nINPUT OBJECT: {}\nREASON: {}]", shootDataDTO,e.toString());
            throw new ResultException(ResultEnum.DUPLICATE_KEY_CONFLICT,"添加射击数据失败\n原因：射击数据id已存在");
        }catch (Exception e) {
            log.error("ADD [shootData] FAIL\nINPUT OBJECT: {}\nREASON: {}]", shootDataDTO, e.toString());
            throw new ResultException(ResultEnum.ERROR, "添加射击数据失败");
        }
        return status;
    }

    @Override
    public int update(ShootDataDTO shootDataDTO) {
        ShootData shootData = new ShootData();
        BeanUtils.copyProperties(shootDataDTO,shootData);

        int status;
        try{
            status = shootDataMapper.update(shootData);
            if (status == MapperConst.OBJECT_NULL){
                throw new DAOException("this [current Result not exist]");
            }
        } catch (DAOException e){
            log.warn("UPDATE [shootData] FAIL\nINPUT OBJECT: {}\nREASON: {}]", shootDataDTO,e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL,"更新射击数据失败\n原因：射击数据不存在");
        } catch (Exception e){
            log.error("UPDATE [shootData] FAIL\nINPUT OBJECT: {}\nREASON: {}]", shootDataDTO,e.toString());
            throw new ResultException(ResultEnum.ERROR,"更新射击数据失败");
        }
        return status;
    }

    @Override
    public int delete(Integer id) {
        int status;
        try {
            status = shootDataMapper.delete(id);
            if (status == MapperConst.OBJECT_NULL) {
                throw new DAOException("this [shoot Data not exist]");
            }
        } catch (DAOException e) {
            log.warn("DELETE [shootData] FAIL\nINPUT OBJECT: {}\nREASON: {}]", id, e.toString());
            throw new ResultException(ResultEnum.RESULT_NULL, "删除射击数据失败\n原因：射击数据不存在");
        } catch (Exception e) {
            log.error("DELETE [shootData] FAIL\nINPUT OBJECT: {}\nREASON: {}]", id, e.toString());
            throw new ResultException(ResultEnum.ERROR, "删除射击数据失败");
        }
        return status;
    }
}
