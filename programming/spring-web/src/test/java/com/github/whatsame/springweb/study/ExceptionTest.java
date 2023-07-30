package com.github.whatsame.springweb.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.ExceptionDepthComparator;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ExceptionTest {

    @Test
    @DisplayName("Exception depth 정렬을 확인한다.")
    void exceptionDepthSorting() {
        /* given */
        final List<Class<? extends Throwable>> exceptions = new ArrayList<>();
        exceptions.add(RuntimeException.class);
        exceptions.add(IllegalArgumentException.class);
        exceptions.add(IllegalFormatException.class);
        Collections.shuffle(exceptions);

        final Class<? extends Throwable> target = DuplicateFormatFlagsException.class;

        /* when */
        exceptions.sort(new ExceptionDepthComparator(target));

        /* then */
        assertThat(exceptions).containsExactly(
                IllegalFormatException.class,
                IllegalArgumentException.class,
                RuntimeException.class
        );
    }
}
