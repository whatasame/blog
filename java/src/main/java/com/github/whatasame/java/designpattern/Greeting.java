package com.github.whatasame.java.designpattern;

public class Greeting implements GreetingService {

    public String getGreeting() {
        System.out.println("[Greeting] 로직 수행");

        return "Hello, World!";
    }
}
