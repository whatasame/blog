package com.github.whatasame.springweb.exceptionhandler.exception;

public class UniqueException extends RuntimeException {

    public UniqueException(final Throwable cause) {
        super(cause);
    }
}