package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("학습 테스트: Lambda")
class LambdaTest {

    @Test
    @DisplayName("람다식에서 외부 변수에 접근할 수 있다.")
    void accessExternalVariable() throws Exception {
        /* given */
        final String msg = "Hello";

        /* when */
        final Supplier<String> supplier = () -> msg + " world!";

        /* then */
        assertThat(supplier.get()).isEqualTo("Hello world!");
    }

    @Test
    @Disabled("컴파일 에러를 보기 위한 테스트 코드")
    @DisplayName("람다식에서 외부 지역 변수를 수정할 수 없다.")
    void canNotModifyExternalVariable() throws Exception {
        /* given */
        String target = "Hello";

        /* when */
//        () -> target = "world!"; // 컴파일 에러!

        /* then */
    }

    @Test
    @DisplayName("람다식 내부에서 지역 변수 복사본을 가지고 있다.")
    void hasCopyLocalVariableInLambda() throws Exception {
        /* given */

        /* when */
        final Supplier<String> supplier = getUpperCaseSupplier("hello");

        /* then */
        assertThat(supplier.get()).isEqualTo("HELLO");
    }

    @Test
    @DisplayName("람다식이 다른 쓰레드에서 실행될 수 있다.")
    void executedInOtherThread() throws Exception {
        /* given */
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        final Supplier<String> supplier = getUpperCaseSupplier("hello");

        /* when */
        executorService.execute(supplier::get);

        /* then */
    }

    private Supplier<String> getUpperCaseSupplier(final String msg) {
        return msg::toUpperCase;
    }

    /**
     * Thread 1: runnable을 false로 만드는 쓰레드 (Main thread)
     * <p>
     * Thread 2: while문을 실행하는 쓰레드
     * <p>
     * 이때 Thread 1에서 runnable을 먼저 false로 변경할 지, Thread 2에서 while문을 먼저 실행할 지 모른다.
     */
    @Test
    @Disabled("컴파일 에러를 보기 위한 테스트 코드")
    @DisplayName("멀티 쓰레드 환경에서 어느 쓰레드가 먼저 실행될 지 모른다.")
    void concurrencyIssue() throws Exception {
        /* given */
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        boolean runnable = true;

        /* when */
        executorService.execute(() -> {
            // 컴파일 에러!
//            while (runnable) {
//                // do operation
//            }
        });
        runnable = false;

        /* then */
    }
}
