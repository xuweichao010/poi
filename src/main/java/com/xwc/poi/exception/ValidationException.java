package com.xwc.poi.exception;

/**
 * 创建人：徐卫超
 * 创建时间：2018/9/6  14:16
 * 功能：
 * 业务：
 */
@SuppressWarnings("unused")
public class ValidationException extends PoiException {

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidationException(Throwable cause) {
        super(cause);
    }

    public ValidationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
