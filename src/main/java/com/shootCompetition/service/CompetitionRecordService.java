package com.shootCompetition.service;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CompetitionRecordDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.vo.CompetitionRecordVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CompetitionRecordService {

    PageInfo<CompetitionRecordVO> page(PageDTO pageDTO, CompetitionRecordDTO competitionRecordDTO);
    /**
     * 根据DTO参数查询比赛记录列表
     * @param competitionRecordDTO 比赛记录
     * @return 比赛记录VO列表
     *
     * 增删查改
     */
    List<CompetitionRecordVO> list(CompetitionRecordDTO competitionRecordDTO);
    int add(CompetitionRecordDTO competitionRecordDTO);
    int update(CompetitionRecordDTO competitionRecordDTO);
    int delete(@Param("id") Integer id);
    CompetitionRecordVO get(@Param("id") Integer id); //后面新增一个 get比赛记录的方法，在controller里用来判断比赛是否开始
}
