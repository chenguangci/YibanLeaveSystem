package com.yiban.exception;

public class DataLossException extends SystemRunTimeException {
    public DataLossException(String message) {
        super(message);
    }

    public DataLossException(String message, Throwable cause) {
        super(message, cause);
    }
}
