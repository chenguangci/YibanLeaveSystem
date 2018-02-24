package com.yiban.exception;

/**
 * 捕获发送网络请求时发生异常
 */
public class SendException extends SystemRunTimeException {

    public SendException(String message) {
        super(message);
    }

    public SendException(String message, Throwable cause) {
        super(message, cause);
    }
}
