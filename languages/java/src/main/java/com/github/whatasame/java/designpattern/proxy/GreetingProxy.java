package com.github.whatasame.java.designpattern.proxy;

import com.github.whatasame.java.designpattern.GreetingService;
import lombok.RequiredArgsConstructor;

/**
 * DI를 이용하여 GreetingService 구현체를 주입받는다.
 * <p>
 * 인터페이스를 의존하므로 OCP와 DIP를 만족한다.
 */
@RequiredArgsConstructor
public class GreetingProxy implements GreetingService {

    private final GreetingService greetingService;

    /**
     * 프록시는 원본 데이터를 수정하지 않는다.
     */
    @Override
    public String getGreeting() {
        System.out.println("-----Proxy start-----");
        final String greeting = greetingService.getGreeting();
        System.out.println("-----Proxy end-----");

        return greeting;
    }
}
