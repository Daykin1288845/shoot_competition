package com.shootCompetition.domain.dto;

import lombok.Data;

/**
 * <p>
 *     分页查询参数DTO
 * </p>
 * @author hqully
 * @version 1.0
 * @date 2022-10-06 09:48
 */
@Data
public class PageDTO {

    /**
     * 要查询的页号
     */
    private Integer pageNum;
    /**
     * 每页的数据量
     */
    private Integer pageSize;
    /**
     * 数据排序字段(升序/降序)
     */
    private String orderBy;

}