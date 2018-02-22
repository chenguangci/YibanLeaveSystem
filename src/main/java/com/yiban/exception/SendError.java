package com.yiban.exception;

/**
 * 捕获发送网络请求时发生异常
 */
public class SendError extends SystemRunTimeError {

    public SendError(String message) {
        super(message);
    }

    public SendError(String message, Throwable cause) {
        super(message, cause);
    }
}
