package com.github.whatasame.java.stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.whatasame.java.stream.vo.Food;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: Stream API")
class StreamExampleTest {

    @Test
    @DisplayName("Food List에서 Food name을 key로, Food을 value로 구성한 Map을 생성한다.")
    void generateFoodByName() {
        /* given */
        final List<Food> foods = List.of(
            new Food("곱창순두부"),
            new Food("매운갈비찜"),
            new Food("마라탕")
        );

        /* when */
        final Map<String, Food> foodByName = foods.stream()
            .collect(Collectors.toMap(Food::getName, Function.identity()));

        /* then */
        assertAll(
            () -> assertThat(foodByName).containsEntry("곱창순두부", new Food("곱창순두부")),
            () -> assertThat(foodByName).containsEntry("매운갈비찜", new Food("매운갈비찜")),
            () -> assertThat(foodByName).containsEntry("마라탕", new Food("마라탕"))
        );
    }

    @Test
    @DisplayName("flatMap을 이용하여 중첩된 리스트를 평면화한다.")
    void flatMap() throws Exception {
        /* given */
        final List<List<Food>> nestedList = List.of(
            List.of(new Food("제육볶음"), new Food("돈까스")),
            List.of(new Food("아메리카노"), new Food("콜라"))
        );

        /* when */
        final List<Food> foodList = nestedList.stream()
            .flatMap(List::stream)
            .toList();

        /* then */
        assertThat(foodList).hasSameElementsAs(
            List.of(new Food("제육볶음"), new Food("돈까스"), new Food("아메리카노"), new Food("콜라"))
        );
    }
}
