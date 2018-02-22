package com.yiban.exception;


/**
 * 捕获所有系统异常
 */
public class SystemRunTimeError extends RuntimeException {
    public SystemRunTimeError(String message) {
        super(message);
    }

    public SystemRunTimeError(String message, Throwable cause) {
        super(message, cause);
    }
}
