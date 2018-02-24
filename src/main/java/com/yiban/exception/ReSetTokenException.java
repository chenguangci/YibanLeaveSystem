package com.yiban.exception;


/**
 * 捕获重置token的异常
 */
public class ReSetTokenException extends SystemRunTimeException {
    public ReSetTokenException(String message) {
        super(message);
    }

    public ReSetTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
