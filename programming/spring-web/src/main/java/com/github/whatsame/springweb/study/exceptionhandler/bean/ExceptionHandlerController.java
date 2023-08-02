package com.github.whatsame.springweb.study.exceptionhandler.bean;

import com.github.whatsame.springweb.study.exceptionhandler.dummy.BothHandleException;
import com.github.whatsame.springweb.study.exceptionhandler.dummy.HierarchyException1;
import com.github.whatsame.springweb.study.exceptionhandler.dummy.HierarchyException2;
import com.github.whatsame.springweb.study.exceptionhandler.dummy.HierarchyException3;
import com.github.whatsame.springweb.study.exceptionhandler.dummy.HierarchyException4;
import com.github.whatsame.springweb.study.exceptionhandler.dummy.UniqueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionHandlerController {

    @GetMapping("/1/2/3")
    public void throw123() {
        throw new HierarchyException1(new HierarchyException2(new HierarchyException3()));
    }

    @GetMapping("/2/1/3")
    public void throw213() {
        throw new HierarchyException2(new HierarchyException1(new HierarchyException3()));
    }

    @GetMapping("/3/1/2")
    public void throw312() {
        throw new HierarchyException3(new HierarchyException1(new HierarchyException2()));
    }

    @GetMapping("/4/1/2")
    public void throw412() {
        throw new HierarchyException4(new HierarchyException1(new HierarchyException2()));
    }

    @GetMapping("/unique/3/1/2")
    public void throwUnique312() {
        throw new UniqueException(
            new HierarchyException3(new HierarchyException1(new HierarchyException2()))
        );
    }

    @GetMapping("/bothHandleException")
    public void throwBothHandleException() {
        throw new BothHandleException();
    }

    @ExceptionHandler(BothHandleException.class)
    public String handleBothHandleException(final Exception exception) {
        return "Controller => " +
            exception.getClass().getSimpleName();
    }
}
