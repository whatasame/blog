# Extension과 @ExtendWith

## Extension이란?

테스트 실행 간에 발생하는 이벤트에 대하여 특정 동작을 실행할 수 있도록한다. 여러 테스트에 대해서 공통적으로 처리해야하는 이벤트가 있는 경우 Extension으로 분리하여 한
곳에서 관리한다.

테스트 실행에서 발생하는 대표적인 이벤트는 다음과 같다.

* test instance post-processing
* conditional test execution
* life-cycle callbacks
* parameter resolution
* exception handling

## Extension의 종류

Extension을 만들기 위해선 JUnit5에서 제공하는 interface를 구현하고 메서드를 override하면 된다.

여러 Extension이 있지만 간단한 예제 작성을 위해 3개의 interface만 구현한다.

* TestInstancePostProcessor: 테스트 인스턴스가 생성된 이후 실행하는 동작
* BeforeEachCallback: 각각의 테스트가 시작되기 전에 실행하는 동작
* InvocationInterceptor: 테스트가 시작된 후 메서드 호출을 가로채 특정 동작을 실행

예제 코드는 다음과 같다.

```java
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

```

## Extension 사용

Extension은 사용하려는 곳에 @ExtendWith의 매개변수로 사용할 수 있다. @ExtendWith는 테스트 클래스, 테스트 인터페이스, 테스트 메서드, 파라미터, 필드에
사용할 수 있다. 이번 예시에서는 테스트 클래스에 사용하였다.

```java

@ExtendWith(PoopExtension.class)
class ExtendWithTest {

}
```

PoopExtension에서 구현한 Extension에 따른 동작 순서는 다음과 같다.

1. 테스트 인스턴스가 생성될 때 `TestInstancePostProcessor`의 `postProcessTestInstance()`를 실행한다.
2. 테스트를 시작하기 전에 `BeforeEachCallback`의 `beforeEach()`를 실행한다.
3. 테스트가 시작되고나서 테스트 안의 beforeEach를 시작하기 전에 `InvocationInterceptor`의 interceptBeforeEachMethod()를
   실행한다.

2번과 3번이 헷갈린다면 학습 테스트를 참고하라.

## 참고 자료

[JUnit 5 User Guide - 5.12. Intercepting Invocations](https://junit.org/junit5/docs/current/user-guide/#extensions-intercepting-invocations)

[A Guide to JUnit 5 Extensions](https://www.baeldung.com/junit-5-extensions)
