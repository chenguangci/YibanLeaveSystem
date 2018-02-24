package com.yiban.exception;

/**
 * 捕获本地信息获取失败的异常
 */
public class UnknownInfoException extends SystemRunTimeException {
    public UnknownInfoException(String message) {
        super(message);
    }

    public UnknownInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
