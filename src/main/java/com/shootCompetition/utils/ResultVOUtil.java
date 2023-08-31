package com.shootCompetition.utils;


import com.shootCompetition.common.enums.ResultEnum;
import com.shootCompetition.domain.vo.ResultVO;

/**
 * <p>
 *     响应数据(结果)最外层对象工具
 * </p>
 * @author liulingyu
 * @date 2022-05-07 16:04
 * @version 1.0
 */
public class ResultVOUtil {

    /**
     * <p>
     *     操作成功
     * </p>
     * @date 2022/5/7 16:04 <br>
     * @author liulingyu <br>
     * @param msg 提示信息
     * @param object 对象
     * @return ResultVo<T>
     */
    public static <T> ResultVO<T> success(String msg, T object) {
        ResultVO<T> resultVo = new ResultVO<>();
        resultVo.setMsg(msg);
        resultVo.setCode(ResultEnum.SUCCESS.getCode());
        resultVo.setData(object);
        return resultVo;
    }

    /**
     * <p>
     *     操作成功
     * </p>
     * @date 2022/5/7 16:04 <br>
     * @author liulingyu <br>
     * @param object 对象
     * @return ResultVo<T>
     */
    public static <T> ResultVO<T> success(T object) {
        String message = ResultEnum.SUCCESS.getMessage();
        return success(message, object);
    }


    /**
     *<p>
     *     操作成功，返回提示信息，不返回数据
     *</p>
     * @date 2022/5/7 16:05 <br>
     * @author liulingyu <br>
     * @param msg 提示信息
     * @return ResultVo<T>
     */
    public static <T> ResultVO<T> success(String msg) {
        return success(msg, null);
    }

    /**
     * <p>
     *     操作成功，不返回数据
     * </p>
     * @date 2022/5/7 16:05 <br>
     * @author liulingyu <br>
     * @return ResultVo<T>
     */
    public static ResultVO success() {
        return success(null);
    }

    /**
     * <p>
     *     操作有误
     * </p>
     * @date 2022/5/7 16:06 <br>
     * @author liulingyu <br>
     * @param code 错误码
     * @param msg 提示信息
     * @return ResultVo
     */
    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVo = new ResultVO();
        resultVo.setMsg(msg);
        resultVo.setCode(code);
        return resultVo;
    }

    public static ResultVO error(ResultEnum resultEnum) {
        ResultVO resultVo = new ResultVO();
        resultVo.setMsg(resultEnum.getMessage());
        resultVo.setCode(resultEnum.getCode());
        return resultVo;
    }


    /**
     * <p>
     *     操作有误，使用默认400错误码
     * </p>
     * @date 2022/5/7 20:38 <br>
     * @author liulingyu <br>
     * @param msg 提示信息
     * @return {@link ResultVO}
     */
    public static ResultVO error(String msg) {
        Integer code = ResultEnum.ERROR.getCode();
        return error(code, msg);
    }

    /**
     * <p>
     *      操作有误，只返回错误码
     * </p>
     * @date 2022/5/7 20:38 <br>
     * @author liulingyu <br>
     * @param
     * @return {@link ResultVO}
     */
    public static ResultVO error() {
        return error("");
    }

}
