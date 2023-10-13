package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: 동기화")
class SynchronizationTest {

    private int sharedCounter;

    @BeforeEach
    void setUp() {
        sharedCounter = 0;
    }

    @Test
    @DisplayName("멀티 쓰레드 환경에서 공유 변수를 동기화하지 않으면 예상치 못한 결과가 발생한다.")
    void concurrentProblem() throws Exception {
        /* given */
        final Runnable incrementTask = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                sharedCounter++;
            }
        };

        final Thread thread2 = new Thread(incrementTask);
        final Thread thread1 = new Thread(incrementTask);

        /* when */
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        /* then */
        assertThat(sharedCounter).isNotEqualTo(2_000_000);
    }

    @Test
    @DisplayName("synchronized 키워드를 이용해 공유 변수를 동기화할 수 있다.")
    void synchronizedVariable() throws Exception {
        /* given */
        final Runnable incrementTask = () -> {
            for (int i = 0; i < 1_000_000; i++) {
                synchronized (this) {
                    sharedCounter++;
                }
            }
        };

        final Thread thread2 = new Thread(incrementTask);
        final Thread thread1 = new Thread(incrementTask);

        /* when */
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        /* then */
        assertThat(sharedCounter).isEqualTo(2_000_000);
    }
}
