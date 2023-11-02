package com.github.whatasame.springweb.exceptionhandler.exception;

public abstract class HierarchException extends RuntimeException {

    protected HierarchException() {
        super();
    }

    protected HierarchException(final Throwable cause) {
        super(cause);
    }
}
