package com.github.whatasame.java.designpattern.proxy;

import com.github.whatasame.java.designpattern.Greeting;
import com.github.whatasame.java.designpattern.GreetingService;
import java.util.Objects;

public class GreetingLazyInitProxy implements GreetingService {

    private GreetingService greetingService;

    @Override
    public String getGreeting() {
        if (Objects.isNull(greetingService)) {
            System.out.println("Lazy initialization!");

            greetingService = new Greeting();
        }

        return greetingService.getGreeting();
    }
}
