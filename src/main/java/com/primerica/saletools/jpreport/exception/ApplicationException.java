package com.primerica.saletools.jpreport.exception;

public class ApplicationException extends Exception {
    private final String errorCode;

    public ApplicationException(String errorCode, String message, Throwable ex) {
        super(message, ex);
        this.errorCode = errorCode;
    }

    public ApplicationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
