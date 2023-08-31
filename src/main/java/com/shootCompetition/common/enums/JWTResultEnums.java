package com.shootCompetition.common.enums;


import com.shootCompetition.common.exception.interfaces.ResultInterface;
import lombok.Getter;

/**
 * <p>
 *jwt结果集枚举
 *</p>
 * @author liulingyu
 * @date 2022-05-07 15:43
 * @version 1.0
 */
@Getter
public enum JWTResultEnums implements ResultInterface {

    /**
     * token问题
     */
    TOKEN_ERROR(30001, "token无效"),
    TOKEN_EXPIRED(30002, "token已过期"),
    TOKEN_EMPTY(30003, "token为空"),
    TOKEN_DECODE_FAILED(30004, "token解析失败"),


    /**
     * 账号问题
     */
    AUTH_REQUEST_ERROR(40001, "用户名或密码错误"),
    AUTH_REQUEST_LOCKED(40002, "该账号已被冻结"),
    ;

    private Integer code;

    private String message;

    JWTResultEnums(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
