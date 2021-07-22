package com.townscript.goodreadsapi.exception;

public class SpringCustomException extends RuntimeException {

    public SpringCustomException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringCustomException(String exMessage) {
        super(exMessage);
    }
}
