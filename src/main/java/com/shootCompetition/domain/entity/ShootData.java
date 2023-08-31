package com.shootCompetition.domain.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@ApiModel(description = "射击数据")
@Data
public class ShootData implements Serializable {
    private static final long serialVersionUID = 664510866634954217L;
    /**
     * 参赛选手某回合射击的数据，包括射击数据id、训练记录名称id、选手id、弹序、射击时间、
     * 命中环数、瞄准环数、据枪晃动量、据枪晃动速率、击发晃动速率、击发晃动量、射击精确性、
     * 据枪稳定性、击发稳定性、本次射击成绩
     */
    @ApiModelProperty("射击数据id")
    private Long id; 

    @ApiModelProperty("训练记录名称id")
    private String recordId;

    @ApiModelProperty("选手id")
    private Long shooterId;

    @ApiModelProperty("弹序")
    private Long sequence;

    @ApiModelProperty("射击时间")
    /*
    解决jackson时间规格不一致的问题
     */
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime shootTime;

    @ApiModelProperty("命中环数")
    private Double aimRingNumber;

    @ApiModelProperty("瞄准环数")
    private Double hitRingNumber;

    @ApiModelProperty("据枪晃动量")
    private Long gunShaking;

    @ApiModelProperty("据枪晃动率")
    private Long gunShakingRate;

    @ApiModelProperty("击发晃动率")
    private Long fireShakingRate;

    @ApiModelProperty("击发晃动量")
    private Long fireShaking;

    @ApiModelProperty("射击精确性")
    private Double shootingAccuracy;

    @ApiModelProperty("据枪稳定性")
    private Double gunStability;

    @ApiModelProperty("击发稳定性")
    private Double fireStability;

    @ApiModelProperty("本次射击成绩")
    private Double score;
}
