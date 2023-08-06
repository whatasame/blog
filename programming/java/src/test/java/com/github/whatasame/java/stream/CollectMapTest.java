package com.github.whatasame.java.stream;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.whatasame.java.stream.vo.Food;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Stream API를 이용하여 Map을 생성한다.")
class CollectMapTest {

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
        assertThat(foodByName.get("매운갈비찜")).isNotNull();
    }
}
