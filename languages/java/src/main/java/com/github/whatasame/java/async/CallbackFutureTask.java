package com.github.whatasame.java.async;

import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask를 상속받아 Callback을 이용한 비동기 작업을 구현한다.
 */
public class CallbackFutureTask extends FutureTask<String> {

    private final SuccessCallback successCallback;
    private final ExceptionCallback exceptionCallback;

    public CallbackFutureTask(
        final Callable<String> callable,
        final SuccessCallback successCallback,
        final ExceptionCallback exceptionCallback
    ) {
        super(callable);

        this.successCallback = Objects.requireNonNull(successCallback);
        this.exceptionCallback = Objects.requireNonNull(exceptionCallback);
    }

    @Override
    protected void done() {
        try {
            successCallback.onSuccess(get());
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            e.printStackTrace();
            exceptionCallback.onError(e.getCause());
        }
    }
}
