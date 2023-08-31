package com.shootCompetition.common.exception;



import com.shootCompetition.common.exception.advice.ResultExceptionAdvice;
import com.shootCompetition.domain.vo.ResultVO;
import com.shootCompetition.utils.ResultVOUtil;
import com.shootCompetition.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>全局统一异常处理</p>
 * @author liulingyu
 * @date 2022-05-07 15:41
 * @version 1.0
 */
@ControllerAdvice
@Slf4j
public class ResultExceptionHandler {


    /**
     *<p>
     *拦截自定义结果异常
     *</p>
     * @date 2022/5/7 19:28 <br>
     * @author liulingyu <br>
     * @param e 异常
     * @return ResultVo
     */
    @ExceptionHandler(ResultException.class)
    @ResponseBody
    public ResultVO resultException(ResultException e){
        return ResultVOUtil.error(e.getCode(), e.getMessage());
    }


    /** 拦截未知的运行时异常 */
    /**
     *<p>
     *拦截未知的运行时异常
     *</p>
     * @date 2022/5/7 19:31 <br>
     * @author liulingyu <br>
     * @param e 未知异常
     * @return {@link ResultVO}
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultVO runtimeException(RuntimeException e) {
        ResultExceptionAdvice resultExceptionAdvice = SpringContextUtil.getBean(ResultExceptionAdvice.class);
        resultExceptionAdvice.runtimeException(e);
        log.error("【系统异常】", e);
        return ResultVOUtil.error(500, "未知错误：EX4399");
    }
}
