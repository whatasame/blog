package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.Thread.State;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: Thread 상태")
class ThreadStateTest {

    @Test
    @DisplayName("쓰레드를 생성하면 NEW 상태를 갖는다.")
    void createThread() throws Exception {
        /* given */
        final Thread thread = new Thread(() -> {
        });

        /* when & then */
        assertThat(thread.getState()).isEqualTo(State.NEW);
    }

    @Test
    @DisplayName("쓰레드를 시작하면 RUNNABLE 상태를 갖는다.")
    void startThread() throws Exception {
        /* given */
        final Thread thread = new Thread(() -> {
            sleep(1000);
        });

        /* when */
        thread.start();

        /*  then */
        assertThat(thread.getState()).isEqualTo(State.RUNNABLE);
    }

    @Test
    @DisplayName("쓰레드가 LOCK을 대기하고 있으면 BLOCKED 상태를 갖는다.")
    void blockThread() throws Exception {
        /* given */
        final Thread thread1 = new Thread(this::getLock);
        final Thread thread2 = new Thread(this::getLock);

        /* when */
        thread1.start();
        thread2.start();
        sleep(100);

        /* then */
        assertThat(thread2.getState()).isEqualTo(State.BLOCKED);
    }

    private synchronized void getLock() {
        while (true) {
        }
    }

    @Test
    @DisplayName("쓰레드가 다른 쓰레드가 끝날 때까지 대기하고 있으면 WAITING 상태를 갖는다.")
    void waitingThread() throws Exception {
        // TODO
    }

    private void sleep(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
