package com.shootCompetition.mapper;

import com.shootCompetition.domain.dto.CompetitionShooterDTO;
import com.shootCompetition.domain.dto.ShooterDTO;
import com.shootCompetition.domain.entity.CompetitionShooter;
import com.shootCompetition.domain.entity.Shooter;
import com.shootCompetition.domain.vo.CompetitionShooterVO;
import com.shootCompetition.domain.vo.ShooterVO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CompetitionShooterMapper {
    /**
     *
     * @param competitionShooterDTO
     * @return
     * 定义Shooter表 增删查改 的mapper接口
     */
    List<CompetitionShooterVO> list(CompetitionShooterDTO competitionShooterDTO);

    int add(CompetitionShooter competitionShooter);
    int update(CompetitionShooter competitionShooter);
    int delete(@Param("shooterId") String id);
}
