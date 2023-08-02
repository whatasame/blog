package com.github.whatsame.springweb.study.exceptionhandler.bean;

import com.github.whatsame.springweb.study.exceptionhandler.dummy.BothHandleException;
import com.github.whatsame.springweb.study.exceptionhandler.dummy.HierarchException;
import com.github.whatsame.springweb.study.exceptionhandler.dummy.HierarchyException1;
import com.github.whatsame.springweb.study.exceptionhandler.dummy.HierarchyException2;
import com.github.whatsame.springweb.study.exceptionhandler.dummy.HierarchyException3;
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
