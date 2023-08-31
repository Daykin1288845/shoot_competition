package com.shootCompetition.mapper;

import com.shootCompetition.domain.dto.ShootDataDTO;
import com.shootCompetition.domain.entity.ShootData;
import com.shootCompetition.domain.vo.ShootDataVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ShootDataMapper {
    List<ShootDataVO> list(ShootDataDTO shootDataDTO);

    int add(ShootData shootData);

    int update(ShootData shootData);

    int delete(@Param("id") Integer id);
}