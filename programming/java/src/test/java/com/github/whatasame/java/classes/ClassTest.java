package com.github.whatasame.java.classes;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ClassTest {

    @Test
    @DisplayName("isAssignableFrom() 동작을 확인한다.")
    void isAssignable() {
        /* given */


        /* when & then */
        assertThat(
            RuntimeException.class.isAssignableFrom(IllegalArgumentException.class)).isTrue();
        assertThat(
            IllegalArgumentException.class.isAssignableFrom(RuntimeException.class)).isFalse();
    }
}
