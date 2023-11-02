package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.whatasame.java.designpattern.Greeting;
import com.github.whatasame.java.designpattern.GreetingService;
import com.github.whatasame.java.designpattern.decorator.GreetingDecorator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: 데코레이터 패턴")
class DecoratorTest {

    @Test
    @DisplayName("데코레이터 패턴을 사용하여 반환 값을 장식한다.")
    void manipulateReturnValue() {
        /* given */
        final GreetingService decorator = new GreetingDecorator(new Greeting());

        /* when & then */
        assertThat(decorator.getGreeting()).isEqualTo("HELLO, WORLD!");
    }
}