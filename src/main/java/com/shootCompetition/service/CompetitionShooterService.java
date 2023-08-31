package com.shootCompetition.service;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CompetitionShooterDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.dto.ShooterDTO;
import com.shootCompetition.domain.vo.CompetitionShooterVO;
import com.shootCompetition.domain.vo.ShooterVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompetitionShooterService {
    PageInfo<CompetitionShooterVO> page(PageDTO pageDTO, CompetitionShooterDTO competitionShooterDTO);

    List<CompetitionShooterVO> list(CompetitionShooterDTO competitionShooterDTO);

    int add(CompetitionShooterDTO competitionShooterDTO);

    int update(CompetitionShooterDTO competitionShooterDTO);

    int delete(@Param("ShooterId") String id);
}
