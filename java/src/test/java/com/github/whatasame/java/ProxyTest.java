package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.whatasame.java.designpattern.Greeting;
import com.github.whatasame.java.designpattern.GreetingService;
import com.github.whatasame.java.designpattern.proxy.GreetingCacheProxy;
import com.github.whatasame.java.designpattern.proxy.GreetingLazyInitProxy;
import com.github.whatasame.java.designpattern.proxy.GreetingProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: 프록시 패턴")
class ProxyTest {

    @Test
    @DisplayName("프록시를 사용하지 않고 객체를 바로 호출한다.")
    void invokeDirectly() {
        /* given */
        final Greeting greeting = new Greeting();

        /* when & then */
        assertThat(greeting.getGreeting()).isEqualTo("Hello, World!");
    }

    @Test
    @DisplayName("프록시를 사용하여 객체를 감싼다.")
    void invokeProxy() {
        /* given */
        final GreetingService proxyService = new GreetingProxy(new Greeting());

        /* when & then */
        assertThat(proxyService.getGreeting()).isEqualTo("Hello, World!");
        // 콘솔 출력 확인: Proxy start!, Proxy end!
    }

    @Test
    @DisplayName("프록시를 사용하여 캐싱할 수 있다.")
    void cacheProxy() {
        /* given */
        final GreetingService cacheService = new GreetingCacheProxy(new Greeting());

        /* when & then */
        assertAll(
            () -> assertThat(cacheService.getGreeting()).isEqualTo("Hello, World!"), // miss
            () -> assertThat(cacheService.getGreeting()).isEqualTo("Hello, World!"), // hit
            () -> assertThat(cacheService.getGreeting()).isEqualTo("Hello, World!") // hit
        );
        // 콘솔 출력 확인: Cache miss!, 로직 수행 1회
    }

    @Test
    @DisplayName("프록시를 사용하여 지연 초기화할 수 있다.")
    void lazyInitializeProxy() {
        /* given */
        final GreetingService lazyService = new GreetingLazyInitProxy();

        /* when & then */
        assertThat(lazyService.getGreeting()).isEqualTo("Hello, World!");
        // 콘솔 출력 확인: Lazy initialization!
    }
}