package com.github.whatsame.springweb.study.exceptionhandler.dummy;

public class UniqueException extends RuntimeException {

    public UniqueException(final Throwable cause) {
        super(cause);
    }
}
