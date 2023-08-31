package com.shootCompetition.common.exception;



import com.shootCompetition.common.enums.ResultEnum;
import com.shootCompetition.common.exception.interfaces.ResultInterface;
import lombok.Getter;
import org.apache.shiro.authc.AuthenticationException;


@Getter
/**
 * <p>自定义结果异常类</p>
 * @author liulingyu
 * @date 2022-05-07 15:36
 * @version 1.0
 */
public class ResultException extends AuthenticationException {

    private Integer code;


    /**
     *<p>统一异常处理</p>
     * @date 2022/5/7 15:36 <br>
     * @author liulingyu <br>
     * @param resultEnum 状态枚举
     * @return ResultException
     */
    public ResultException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    /**
     *<p>统一异常处理</p>
     * @date 2022/5/7 15:36 <br>
     * @author liulingyu <br>
     * @param resultEnum 状态枚举
     * @return ResultException
     */
    public ResultException(ResultInterface resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    /**
     *<p>统一异常处理</p>
     * @date 2022/5/7 15:38 <br>
     * @author liulingyu <br>
     * @param code 异常状态码
     * @param message 提示信息
     * @return ResultException
     */
    public ResultException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ResultException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
    }



}
