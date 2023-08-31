package com.shootCompetition.common.exception;

import com.shootCompetition.common.enums.JWTResultEnums;

/**
 * <p>
 * 自定义JWT异常类
 * </p>
 * @author liulingyu
 * @date 2022-05-07 15:27
 * @version 1.0
 */
public class JWTException extends ResultException {


    /**
     *<p>
     *异常类构造方法，获取异常状态码以及枚举类默认异常信息
     *</p>
     * @date 2022/5/7 15:03 <br>
     * @author liulingyu <br>
     * @param jwtResultEnum jwt结果枚举类
     * @return JWTException
     */
    public JWTException(JWTResultEnums jwtResultEnum) {
        super(jwtResultEnum);

    }
    /**
     *<p>
     *异常类构造方法，获取异常状态码以及捕获到的异常信息
     *</p>
     * @date 2022/5/7 15:03 <br>
     * @author liulingyu <br>
     * @param jwtResultEnum jwt结果枚举类
     * @param msg 捕获到的异常的信息
     * @return JWTException
     */
    public JWTException(JWTResultEnums jwtResultEnum , String msg) {
        super(jwtResultEnum.getCode(),msg);

    }
}
