package com.yiban.exception;

/**
 * 捕获本地信息获取失败的异常
 */
public class UnknownInfoError extends SystemRunTimeError{
    public UnknownInfoError(String message) {
        super(message);
    }

    public UnknownInfoError(String message, Throwable cause) {
        super(message, cause);
    }
}
