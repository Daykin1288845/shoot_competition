package com.shootCompetition.service;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.dto.ShooterDTO;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import com.shootCompetition.domain.vo.ShooterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShooterService {
    PageInfo<ShooterVO> page(PageDTO pageDTO, ShooterDTO shooterDTO);

    List<ShooterVO> list(ShooterDTO shooterDTO);

    int add(ShooterDTO shooterDTO);

    int update(ShooterDTO shooterDTO);

    int delete(@Param("id") Integer id);
}
