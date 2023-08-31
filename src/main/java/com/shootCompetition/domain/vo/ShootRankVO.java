package com.shootCompetition.domain.vo;

import lombok.Data;

@Data
public  class ShootRankVO {
    /**
     * 选手id
     */
    public  int shooterId;

    /**
     * 分数
     */
    public  double score;

    /**
     * 弹序
     */
    public  int sequence;

    /**
     * 姓名
     */
    public  String name;

    /**
     * 命中环数
     */
    public  double ring;

    /*
    总计环数
     */
    public double totalRing;


}
