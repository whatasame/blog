package com.github.whatasame.java.annotation;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnnotationTest {

    @Test
    @DisplayName("Annotation에 element가 여러 개일 때 value element가 있다면 이름을 지정하지 않았을 때 value로 지정된다.")
    void value() {
        /* given */
        final Class<Maratang> maratangClass = Maratang.class;
        final FoodName foodName = maratangClass.getAnnotation(FoodName.class);

        /* when */
        final String value = foodName.value();
        final String english = foodName.english();

        /* then */
        assertThat(value).isEqualTo("마라탕");
        assertThat(english).isEmpty();
    }
}
