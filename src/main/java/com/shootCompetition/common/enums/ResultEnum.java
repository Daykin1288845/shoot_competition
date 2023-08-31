package com.shootCompetition.common.enums;


import lombok.Getter;

/**
 * <p>
 * 后台返回结果集枚举
 * </p>
 * @author liulingyu
 * @date 2022-05-07 15:43
 * @version 1.0
 */
@Getter
public enum ResultEnum  {

    /**
     * 通用状态
     */
    SUCCESS(20000, "成功"),
    ERROR(40000, "错误"),

    /**
     * 账户问题
     */
    USER_EXIST(40001, "该用户名已经存在"),
    USER_PWD_NULL(40002, "密码不能为空"),
    USER_INEQUALITY(40003, "两次密码不一致"),
    USER_OLD_PWD_ERROR(40004, "原来密码不正确"),
    USER_NAME_PWD_NULL(40005, "用户名和密码不能为空"),
    USER_CAPTCHA_ERROR(40006, "验证码错误"),
    USER_LOGOUT(40007, "用户已退出登录"),

    /**
     * 角色问题
     */
    ROLE_EXIST(40001, "该角色标识已经存在，不允许重复！"),

    /**
     * 部门问题
     */
    DEPT_EXIST_USER(40001, "部门存在用户，无法删除"),

    /**
     * 字典问题
     */
    DICT_EXIST(40001, "该字典标识已经存在，不允许重复！"),

    /**
     * 非法操作
     */
    STATUS_ERROR(40001, "非法操作：状态有误"),

    /**
     * 权限问题
     */
    NO_PERMISSIONS(40001, "权限不足！"),
    NO_ADMIN_AUTH(50000, "不允许操作超级管理员"),
    NO_ADMIN_STATUS(50001, "不能修改超级管理员状态"),
    NO_ADMINROLE_AUTH(50000, "不允许操作管理员角色"),
    NO_ADMINROLE_STATUS(50001, "不能修改管理员角色状态"),

    /**
     * 学籍信息问题
     */
    GRADE_NULL(600010,"年级为空"),
    GRADE_NOT_EXIST(600011,"年级不存在"),
    COLLEGE_NULL(600020,"学院为空"),
    COLLEGE_NOT_EXIST(600021,"学院不存在"),
    MAJOR_NULL(600030,"专业为空"),
    MAJOR_NOT_EXIST(600031,"专业不存在"),
    CLAZZ_NULL(600040,"班级为空"),
    CLAZZ_NOT_EXIST(600041,"班级不存在"),

    /**
     * 数据库问题
     */
    PRIMARY_KEY_CONFLICT(50001,"主键冲突"),
    DUPLICATE_KEY_CONFLICT(50002,"键冲突"),
    RESULT_NULL(50003,"返回数据为空")
    ;

    private Integer code;

    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
