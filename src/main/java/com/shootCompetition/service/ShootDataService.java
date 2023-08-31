package com.shootCompetition.service;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.dto.ShootDataDTO;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.domain.vo.ShootDataVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShootDataService {
    PageInfo<ShootDataVO> page(PageDTO pageDTO, ShootDataDTO shootDataDTO);

    List<ShootDataVO> list(ShootDataDTO shootDataDTO);

    int add(ShootDataDTO shootDataDTO);

    int update(ShootDataDTO shootDataDTO);

    int delete(@Param("id") Integer id);
}
