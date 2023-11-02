package com.github.whatasame.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.whatasame.springdatajpa.count.Count;
import com.github.whatasame.springdatajpa.count.CountRepository;
import com.github.whatasame.springdatajpa.count.CountService;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@DisplayName("학습 테스트: 동시성 문제")
class ConcurrencyTest {

    private static final int THREAD_COUNT = 1000;
    private static final long CLICK_COUNT = 10;

    @Autowired
    CountService countService;

    @Autowired
    CountRepository countRepository;

    @Test
    @DisplayName("락을 사용하지 않으면 동시성 문제가 발생한다.")
    void occurConcurrencyProblem() throws Exception {
        final long startTime = System.currentTimeMillis();

        /* given */
        final Count original = countRepository.save(new Count());
        countRepository.flush();

        /* when */
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < CLICK_COUNT; j++) {
                        countService.increaseCountOne(original.getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        /* then */
        final Count updated = countRepository.findById(original.getId()).get();
        assertThat(updated.getSize()).isNotEqualTo(THREAD_COUNT * CLICK_COUNT);

        final long endTime = System.currentTimeMillis();
        System.out.println("테스트 시간: " + (endTime - startTime) + "ms");
        System.out.println("총 카운트 수: " + updated.getSize());
    }

    @Test
    @DisplayName("비관적 락(읽기)를 사용하여 동시성 문제를 해결한다.")
    void resolveConcurrencyProblemWithPessimisticRead() throws Exception {
        final long startTime = System.currentTimeMillis();

        /* given */
        final Count original = countRepository.save(new Count());
        countRepository.flush();

        /* when */
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < CLICK_COUNT; j++) {
                        countService.increaseCountOneWithPessimisticRead(original.getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        /* then */
        final Count updated = countRepository.findById(original.getId()).get();
        assertThat(updated.getSize()).isEqualTo(THREAD_COUNT * CLICK_COUNT);

        final long endTime = System.currentTimeMillis();
        System.out.println("테스트 시간: " + (endTime - startTime) + "ms");
        System.out.println("총 카운트 수: " + updated.getSize());
    }

    @Test
    @DisplayName("비관적 락(쓰기)를 사용하여 동시성 문제를 해결한다.")
    void resolveConcurrencyProblemWithPessimisticWrite() throws Exception {
        final long startTime = System.currentTimeMillis();

        /* given */
        final Count original = countRepository.save(new Count());
        countRepository.flush();

        /* when */
        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
        CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);
        for (int i = 0; i < THREAD_COUNT; i++) {
            executorService.submit(() -> {
                try {
                    for (int j = 0; j < CLICK_COUNT; j++) {
                        countService.increaseCountOneWithPessimisticWrite(original.getId());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();

        /* then */
        final Count updated = countRepository.findById(original.getId()).get();
        assertThat(updated.getSize()).isEqualTo(THREAD_COUNT * CLICK_COUNT);

        final long endTime = System.currentTimeMillis();
        System.out.println("테스트 시간: " + (endTime - startTime) + "ms");
        System.out.println("총 카운트 수: " + updated.getSize());
    }
}
