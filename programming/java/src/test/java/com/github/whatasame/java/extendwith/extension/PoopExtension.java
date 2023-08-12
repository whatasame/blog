package com.github.whatasame.java.extendwith.extension;

import java.lang.reflect.Method;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.InvocationInterceptor;
import org.junit.jupiter.api.extension.ReflectiveInvocationContext;
import org.junit.jupiter.api.extension.TestInstancePostProcessor;

public class PoopExtension implements
    TestInstancePostProcessor,
    BeforeEachCallback,
    InvocationInterceptor {

    @Override
    public void postProcessTestInstance(
        final Object testInstance,
        final ExtensionContext context
    ) throws Exception {
        System.out.println("응애 나 테스트 인스턴스");
    }

    @Override
    public void beforeEach(final ExtensionContext context) throws Exception {
        System.out.println("테스트 시작하기전에 이건 해야지. 이제 테스트 시작해라");
    }

    @Override
    public void interceptBeforeEachMethod(
        final Invocation<Void> invocation,
        final ReflectiveInvocationContext<Method> invocationContext,
        final ExtensionContext extensionContext
    ) throws Throwable {
        System.out.println("테스트 시작했는데 beforeEach() 실행하기전에 이거좀 하자");

        InvocationInterceptor.super
            .interceptBeforeEachMethod(invocation, invocationContext, extensionContext);
    }
}
