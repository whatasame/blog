package com.github.whatsame.springweb.study.exceptionhandler.dummy;

public abstract class HierarchException extends RuntimeException {

    protected HierarchException() {
        super();
    }

    protected HierarchException(final Throwable cause) {
        super(cause);
    }
}
