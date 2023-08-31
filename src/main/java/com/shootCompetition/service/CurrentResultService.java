package com.shootCompetition.service;

import com.github.pagehelper.PageInfo;
import com.shootCompetition.domain.dto.CurrentResultDTO;
import com.shootCompetition.domain.dto.PageDTO;
import com.shootCompetition.domain.vo.CurrentResultVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CurrentResultService {
    PageInfo<CurrentResultVO> page(PageDTO pageDTO, CurrentResultDTO currentResultDTO);
    /**
     *
     * @param currentResultDTO 某选手比赛得分
     * @return 比赛得分VO列表
     */
    List<CurrentResultVO> list(CurrentResultDTO currentResultDTO);
    int add(CurrentResultDTO currentResultDTO);
    int update(CurrentResultDTO currentResultDTO);
    int delete(@Param("id") Integer id);

}
