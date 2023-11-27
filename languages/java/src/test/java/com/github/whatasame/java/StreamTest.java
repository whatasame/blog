package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

import com.github.whatasame.java.stream.vo.Student;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: Stream API")
class StreamTest {

    @Test
    @DisplayName("외부에 선언된 변수를 수정할 수 없다.")
    void canNotModifyEternalVariable() throws Exception {
        /* given */
        final List<String> 오늘먹은것 = List.of("참치마요삼각김밥", "크리미유부초밥", "진라면매운맛", "제육볶음");

        /* when */
        final List<String> mapped = 오늘먹은것.stream()
                .map(food -> food = food + " 먹었음")
                .toList();

        /* then */
        assertThat(오늘먹은것).hasSameElementsAs(mapped);
    }

    @Test
    @DisplayName("최종 연산이 없으면 중간 연산은 실행되지 않는다.")
    void noTerminalOperation() throws Exception {
        /* given */
        final List<Integer> nums = List.of(1, 2, 3, 4, 5);

        /* when */
        nums.stream()
                .filter(num -> {
                    System.out.println("filter target = " + num);
                    return num % 2 == 0;
                })
                .map(num -> {
                    System.out.println("map target = " + num);
                    return num * 2;
                });

        /* then */
        // 아무것도 출력되지 않는다.
    }

    @Test
    @DisplayName("한 번 사용한 스트림은 재사용할 수 없다.")
    void notReusable() throws Exception {
        /* given */
        final Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        stream.count();

        /* when & then */
        assertThatCode(() -> stream.count())
                .isExactlyInstanceOf(IllegalStateException.class)
                .hasMessage("stream has already been operated upon or closed");
    }

    @Test
    @DisplayName("매개변수에 이름을 붙여 가독성을 높인다.")
    void readabilityWithNamingArgument() throws Exception {
        /* given */
        final List<Student> students = List.of(
                new Student("kim", 100, 30, 60),
                new Student("lee", 40, 90, 90),
                new Student("park", 10, 80, 50)
        );

        /* when */
        final Double maxGrade = students.stream()
                .map(student -> Math.max(student.korean(),
                        Math.max(student.math(), student.english())))
                .reduce((bef, next) -> bef > next ? bef : next)
                .orElseThrow();

        /* then */
        assertThat(maxGrade).isEqualTo(100);
    }


    @Test
    @DisplayName("도우미 메서드를 이용하여 가독성을 높인다.")
    void readabilityWithHelperMethod() throws Exception {
        /* given */
        final List<Student> students = List.of(
                new Student("kim", 100, 30, 60),
                new Student("lee", 40, 90, 90),
                new Student("park", 10, 80, 50)
        );

        /* when */
        final Double maxGrade = students.stream()
                .map(Student::maxGrade)
                .reduce((bef, next) -> bef > next ? bef : next)
                .orElseThrow();

        /* then */
        assertThat(maxGrade).isEqualTo(100);
    }
}
