package com.shootCompetition.mapper;

import com.shootCompetition.domain.dto.CurrentResultDTO;
import com.shootCompetition.domain.entity.CurrentResult;
import com.shootCompetition.domain.vo.CurrentResultVO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CurrentResultMapper {
    /**
     *
     * @param currentResultDTO 某选手当前比赛结果
     * @return 比赛结果VO列表
     */
    List<CurrentResultVO> list(CurrentResultDTO currentResultDTO);

    int add(CurrentResult currentResult);
    int update(CurrentResult currentResult);
    int delete(@Param("id") Integer id);
}
