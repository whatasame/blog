package com.github.whatasame.java.designpattern.decorator;

import com.github.whatasame.java.designpattern.GreetingService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GreetingDecorator implements GreetingService {

    private final GreetingService greetingService;

    /**
     * 인사말을 대문자로 장식하여(decorate) 반환한다.
     */
    @Override
    public String getGreeting() {
        return greetingService.getGreeting().toUpperCase();
    }
}
