package com.github.whatasame.java.designpattern.proxy;

import com.github.whatasame.java.designpattern.GreetingService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GreetingCacheProxy implements GreetingService {

    private final GreetingService greetingService;
    private String cachedGreeting;

    @Override
    public String getGreeting() {
        if (Objects.isNull(cachedGreeting)) {
            System.out.println("Cache miss!");

            cachedGreeting = greetingService.getGreeting();
        }

        return cachedGreeting;
    }
}
