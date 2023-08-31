package com.shootCompetition.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;


@Data
public class CurrentResultDTO implements Serializable {
    private static final long serialVersionUID = 864510876634952217L;


    /**
     * 选手id
     */
    @ApiModelProperty("选手id")
    private Long id;

    /**
     * 选手姓名
     */
    @ApiModelProperty("选手姓名")
    private String name;

    /**
     * 累计得分
     */
    @ApiModelProperty("累计得分")
    private Long totalScore;

    /**
     * 某回合的成绩对应比赛记录id
     */
    @ApiModelProperty("某回合成绩对应比赛记录id")
    private Long shootDataId;
}
