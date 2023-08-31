package com.shootCompetition.domain.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@ApiModel(description = "选手基本信息表")
@Data
public class ShooterVO implements Serializable {
    private static final long serialVersionUID = 234510866634954217L;
    /**
     * 参赛选手Shooter表的相关字段，包括选手id、姓名、年龄、上场次序、所属队伍、比赛记录id
     */
    @ApiModelProperty("选手id")
    private Long id;

    @ApiModelProperty("选手姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Long age;

    @ApiModelProperty("上场次序")
    private Long sequence;

    @ApiModelProperty("所属队伍")
    private String team;

    @ApiModelProperty("比赛记录id")
    private Long recordId;

}
