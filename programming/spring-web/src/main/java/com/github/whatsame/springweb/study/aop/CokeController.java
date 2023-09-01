package com.github.whatsame.springweb.study.aop;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CokeController {

    @RequestLogging
    @PostMapping
    public String coke(
        @RequestParam final String name,
        @RequestParam final double volume,
        final String greeting
    ) {
        return greeting + "콜라 이름은 " + name + "이고 용량은 " + volume + "이다.";
    }
}
