package com.yiban.exception;


/**
 * 捕获网络请求成功，但接口调用出现错误的异常
 *
 */
public class RequestInfoError extends SystemRunTimeError {

    public RequestInfoError(String message) {
        super(message);
    }

    public RequestInfoError(String message, Throwable cause) {
        super(message, cause);
    }
}
