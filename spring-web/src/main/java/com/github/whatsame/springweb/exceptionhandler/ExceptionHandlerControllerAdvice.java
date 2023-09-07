package com.github.whatsame.springweb.exceptionhandler;

import com.github.whatsame.springweb.exceptionhandler.exception.BothHandleException;
import com.github.whatsame.springweb.exceptionhandler.exception.HierarchException;
import com.github.whatsame.springweb.exceptionhandler.exception.HierarchyException1;
import com.github.whatsame.springweb.exceptionhandler.exception.HierarchyException2;
import com.github.whatsame.springweb.exceptionhandler.exception.HierarchyException3;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerControllerAdvice {

    @ExceptionHandler({HierarchyException1.class, HierarchyException2.class})
    public String handle12(final HierarchException exception) {
        return "ExceptionHandlerTestHandler#handle12 => " + exception.getClass().getSimpleName();
    }

    @ExceptionHandler
    public String handle3(final HierarchyException3 exception) {
        return "ExceptionHandlerTestHandler#handle3 => " + exception.getClass().getSimpleName();
    }

    @ExceptionHandler(BothHandleException.class)
    public String handleBothHandleException(final RuntimeException exception) {
        return "ControllerAdvice => " +
            exception.getClass().getSimpleName();
    }
}
