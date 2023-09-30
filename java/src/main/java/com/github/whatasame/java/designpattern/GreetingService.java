package com.github.whatasame.java.designpattern;

/**
 * GreetingService를 사용하는 클라이언트는 인터페이스에 의존하므로 구현체가 변경되더라도 영향을 받지 않는다.
 * <p>
 * 따라서 OCP와 DIP를 만족한다.
 */
public interface GreetingService {

    String getGreeting();
}
