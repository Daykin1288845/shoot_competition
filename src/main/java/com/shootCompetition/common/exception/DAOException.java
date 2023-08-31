package com.shootCompetition.common.exception;

/**
 * <p>
 * dao exception
 * <p>
 *
 * @author liulingyu
 * @date 2022/8/26 9:21
 * @version 1.0
 */
public class DAOException extends RuntimeException{
    public DAOException() {
    }

    public DAOException(String message) {
        super(message);
    }

    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }

    public DAOException(Throwable cause) {
        super(cause);
    }

    public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
