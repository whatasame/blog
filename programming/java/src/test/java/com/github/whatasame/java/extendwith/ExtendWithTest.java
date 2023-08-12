package com.github.whatasame.java.extendwith;

import com.github.whatasame.java.extendwith.extension.PoopExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(PoopExtension.class)
class ExtendWithTest {

    /**
     * <h3>각 테스트 메서드 호출마다 Extension 동작 확인</h3>
     * <ul>
     *     <li>TestInstancePostProcessor: 테스트 클래스 인스턴스 생성 시</li>
     *     <li>BeforeEachCallback: 테스트 시작하기 전에</li>
     *     <li>InvocationInterceptor: 테스트 실행 후 beforeEach() 실행 시</li>
     * </ul>
     */

    @BeforeEach
    void setUp() {
        System.out.println("나 이제 진짜 beforeEach 실행한다.");
    }

    @Test
    void dummy1() {
        /* no-op */
        System.out.println("테스트 중..");
    }

    @Test
    void dummy2() {
        System.out.println("테스트 중..");
    }

    @Test
    void dummy3() {
        System.out.println("테스트 중..");
    }
}
