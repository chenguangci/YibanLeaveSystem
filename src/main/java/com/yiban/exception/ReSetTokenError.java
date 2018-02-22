package com.yiban.exception;


/**
 * 捕获重置token的异常
 */
public class ReSetTokenError extends SystemRunTimeError {
    public ReSetTokenError(String message) {
        super(message);
    }

    public ReSetTokenError(String message, Throwable cause) {
        super(message, cause);
    }
}
