package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: 병렬 스트림")
class ParallelStreamTest {

    @Test
    @DisplayName("일반 스트림은 별도의 쓰레드를 생성하지 않는다.")
    void streamThread() throws Exception {
        /* given */
        System.out.println("메인 쓰레드: " + Thread.currentThread().getName());

        /* when */
        Stream.of(1, 2, 3, 4, 5)
            .forEach(num -> System.out.println(
                "일반 스트림 쓰레드: " + Thread.currentThread().getName() + ", 데이터: " + num));

        /* then */
        // 메인 쓰레드와 스트림 쓰레드가 동일한 것을 확인한다.
    }

    @Test
    @DisplayName("병렬 스트림은 별도의 쓰레드를 생성한다.")
    void parallelStreamThread() throws Exception {
        /* given */
        System.out.println("메인 쓰레드: " + Thread.currentThread().getName());

        /* when */
        Stream.of(1, 2, 3, 4, 5)
            .parallel()
            .forEach(num -> System.out.println(
                "병렬 스트림 쓰레드: " + Thread.currentThread().getName() + ", 데이터: " + num));

        /* then */
        // 메인 쓰레드와 스트림 쓰레드가 다른 것을 확인한다.
    }

    @Test
    @Disabled("실행 시간이 오래 걸리므로 필요할 때만 테스트한다.")
    @DisplayName("데이터 수가 많을 경우 병렬 스트림이 빠를 수 있다.")
    void parallelStreamWithManyData() throws Exception {
        // given

        // when
        final long streamStart = System.currentTimeMillis();
        LongStream.rangeClosed(2, 10_000_000)
            .mapToObj(BigInteger::valueOf)
            .filter(num -> num.isProbablePrime(50))
            .count();
        final long streamElapsed = System.currentTimeMillis() - streamStart;

        final long parallelStart = System.currentTimeMillis();
        LongStream.rangeClosed(2, 10_000_000)
            .parallel()
            .mapToObj(BigInteger::valueOf)
            .filter(num -> num.isProbablePrime(50))
            .count();
        final long parallelElapsed = System.currentTimeMillis() - parallelStart;

        // then
        assertThat(parallelElapsed).isLessThan(streamElapsed);
        System.out.println("병렬 스트림: " + parallelElapsed + "ms");
        System.out.println("일반 스트림: " + streamElapsed + "ms");
        System.out.println("시간 차이: " + (streamElapsed - parallelElapsed) + "ms");
    }

    @Test
    @DisplayName("데이터 수가 적을 경우 병렬 스트림이 오히려 느리다.")
    void parallelStreamWithFewData() throws Exception {
        // given
        final ArrayList<Integer> nums = new ArrayList<>();
        for (int num = 0; num < 100; num++) {
            nums.add(num);
        }

        // when
        final long parallelStart = System.currentTimeMillis();
        nums.parallelStream()
            .mapToLong(Integer::longValue)
            .sum();
        final long parallelElapsed = System.currentTimeMillis() - parallelStart;

        final long streamStart = System.currentTimeMillis();
        nums.stream()
            .mapToLong(Integer::longValue)
            .sum();
        final long streamElapsed = System.currentTimeMillis() - streamStart;

        // then
        assertThat(parallelElapsed).isGreaterThan(streamElapsed);
        System.out.println("병렬 스트림: " + parallelElapsed + "ms");
        System.out.println("일반 스트림: " + streamElapsed + "ms");
        System.out.println("시간 차이: " + (parallelElapsed - streamElapsed) + "ms");
    }
}
