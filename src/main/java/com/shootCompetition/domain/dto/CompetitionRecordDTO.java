package com.shootCompetition.domain.dto;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 比赛记录实体类
 */
@ApiModel(description = "比赛记录表")
@Data
public class CompetitionRecordDTO implements Serializable {
    private static final long serialVersionUID = 340368193900764163L;

    /**
     * 比赛id
     */
    @ApiModelProperty("比赛id")
    private Long id;

    /**
     * 比赛开始时间
     */
    @ApiModelProperty("比赛开始时间")
    private LocalDateTime startTime;

    /**
     * 比赛结束时间
     */
    @ApiModelProperty("比赛结束时间")
    private LocalDateTime endTime;

    /**
     * 比赛名称，第几轮
     */
    @ApiModelProperty("比赛名称")
    private String name;

}
