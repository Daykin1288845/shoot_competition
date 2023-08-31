package com.shootCompetition.mapper;

import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.entity.CompetitionRecord;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface CompetitionRecordMapper {
    /**
     * 根据DTO参数查询比赛记录列表
     * @param competitionRecordDTO 比赛记录
     * @return 比赛记录VO列表
     *
     */
    List<CompetitionRecordVO> list(CompetitionRecordDTO competitionRecordDTO);
    int add(CompetitionRecord competitionRecord);
    int update(CompetitionRecord competitionRecord);
    int delete(@Param("id") Integer id);
    CompetitionRecordVO get(@Param("id") Integer id);


}
