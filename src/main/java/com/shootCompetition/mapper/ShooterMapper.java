package com.shootCompetition.mapper;

import com.shootCompetition.domain.dto.ShooterDTO;
import com.shootCompetition.domain.entity.Shooter;
import com.shootCompetition.domain.vo.ShooterVO;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ShooterMapper {
    /**
     *
     * @param shooterDTO
     * @return
     * 定义Shooter表 增删查改 的mapper接口
     */
    List<ShooterVO> list(ShooterDTO shooterDTO);

    int add(Shooter shooter);
    int update(Shooter shooter);
    int delete(@Param("id") Integer id);
}
