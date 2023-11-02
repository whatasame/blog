package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.whatasame.java.async.CallbackFutureTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: Java 비동기 프로그래밍")
class JavaAsyncTest {

    private ExecutorService executorService;

    @BeforeEach
    void setUp() {
        this.executorService = Executors.newCachedThreadPool();
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        this.executorService.shutdown();
        this.executorService.awaitTermination(1, TimeUnit.HOURS);
    }

    @Test
    @DisplayName("Future.get()을 이용하면 비동기 작업 결과를 사용하려면 기다려야한다.")
    void futureGet() throws Exception {
        /* given */
        final long start = System.currentTimeMillis();
        final Future<String> future1 = executorService.submit(() -> {
            Thread.sleep(2000);
            System.out.println("Thread: " + Thread.currentThread().getName());
            return "First async task result";
        });
        final Future<String> future2 = executorService.submit(() -> {
            Thread.sleep(3000);
            System.out.println("Thread: " + Thread.currentThread().getName());
            return "Second async task result";
        });

        /* when */
        System.out.println("첫 번째 비동기 작업 결과: " + future1.get());
        System.out.println("두 번째 비동기 작업 결과: " + future2.get());

        /* then */
        final long elapsed = System.currentTimeMillis() - start;
        assertThat(elapsed).isGreaterThanOrEqualTo(3000);
        System.out.println("Elapsed: " + elapsed + "ms");
    }

    @Test
    @DisplayName("Callback을 이용하면 비동기 작업을 기다리지 않고 추가 작업을 실행할 수 있다.")
    void callback() throws Exception {
        /* given */
        final long start = System.currentTimeMillis();
        final CallbackFutureTask callbackFutureTask = new CallbackFutureTask(
            () -> {
                Thread.sleep(2000);
                System.out.println("Thread: " + Thread.currentThread().getName());
                return "First async task";
            },
            result -> System.out.println("SUCCESS: " + result),
            error -> System.out.println("ERROR: " + error.getMessage())
        );

        /* when */
        executorService.execute(callbackFutureTask);

        /* then */
        final long elapsed = System.currentTimeMillis() - start;
        assertThat(elapsed).isLessThan(2000);
        System.out.println("Elapsed: " + elapsed + "ms");
    }
}
