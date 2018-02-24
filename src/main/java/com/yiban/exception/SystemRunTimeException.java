package com.yiban.exception;


/**
 * 捕获所有系统异常
 */
public class SystemRunTimeException extends RuntimeException {
    public SystemRunTimeException(String message) {
        super(message);
    }

    public SystemRunTimeException(String message, Throwable cause) {
        super(message, cause);
    }
}
