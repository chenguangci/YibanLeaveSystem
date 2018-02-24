package com.yiban.exception;


/**
 * 捕获网络请求成功，但接口调用出现错误的异常
 *
 */
public class RequestInfoException extends SystemRunTimeException {

    public RequestInfoException(String message) {
        super(message);
    }

    public RequestInfoException(String message, Throwable cause) {
        super(message, cause);
    }
}
