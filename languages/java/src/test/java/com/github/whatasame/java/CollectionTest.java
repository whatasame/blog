package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: Collection")
class CollectionTest {

    @Test
    @DisplayName("동일한 Set을 Set에 추가하면 중복을 허용하지 않는다.")
    void NotAllowedDuplication() {
        /* given */
        final Set<Set<Integer>> nestedSet = new HashSet<>();

        /* when */
        nestedSet.add(Set.of(1, 2, 3));
        nestedSet.add(Set.of(1, 2, 3));

        /* then */
        assertThat(nestedSet).hasSize(1);
    }

    @Test
    @DisplayName("Set에 mutable Set을 추가한 후 mutable Set을 수정하면 Set에서 중복이 발생할 수 있다.")
    void modifyingMutableSetMayCauseDuplication() {
        /* given */
        final Set<Integer> set1 = new HashSet<>();
        set1.add(1);
        set1.add(2);

        final Set<Integer> set2 = new HashSet<>();
        set2.add(1);
        set2.add(3);

        final Set<Set<Integer>> nestedSet = Set.of(set1, set2);

        /* when */
        set1.add(3);
        set2.add(2);

        /* then */
        assertThat(set1).isEqualTo(set2);
        assertThat(nestedSet).hasSize(2); // expected size is 1
    }
}
