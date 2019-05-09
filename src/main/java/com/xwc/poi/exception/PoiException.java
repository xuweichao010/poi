package com.xwc.poi.exception;

/**
 * 创建人：徐卫超
 * 创建时间：2018/10/24  9:56
 * 业务：
 * 功能：
 */
public class PoiException extends RuntimeException {
    public PoiException() {
        super();
    }

    public PoiException(String message) {
        super(message);
    }

    public PoiException(String message, Throwable cause) {
        super(message, cause);
    }

    public PoiException(Throwable cause) {
        super(cause);
    }

    protected PoiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
