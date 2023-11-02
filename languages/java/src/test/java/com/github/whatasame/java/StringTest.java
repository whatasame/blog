package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: String")
class StringTest {

    @Test
    @DisplayName("리터럴로 선언한 String은 String pool에서 관리된다.")
    void stringPool() {
        /* given */
        final String first = "hello";
        final String second = "hello";
        final String third = "hello";

        /* when & then */
        assertThat(first).isSameAs(second).isSameAs(third);
    }

    @Test
    @DisplayName("new로 생성한 String은 String pool에서 관리되지 않는다.")
    void newString() {
        /* given */
        final String first = new String("hello");
        final String second = new String("hello");

        /* when & then */
        assertThat(first).isNotSameAs(second);
    }
}
