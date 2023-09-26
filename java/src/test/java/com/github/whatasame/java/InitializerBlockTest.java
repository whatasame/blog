package com.github.whatasame.java;

import com.github.whatasame.java.basic.InitializerBlock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

/**
 * 클래스 로드 여부가 테스트 결과에 영향을 주므로 부득이하게 일부 테스트의 순서를 지정했다.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class InitializerBlockTest {

    @Test
    @Order(1)
    @DisplayName("클래스를 생성하지 않으면 컴파일러가 클래스를 로드하지 않기 때문에 static initializer block은 실행되지 않는다.")
    void doesNotExecuteInitializerBlock() throws Exception {
        // given

        // when

        // then
    }

    @Test
    @Order(2)
    @DisplayName("인스턴스 최초 생성 시 컴파일러가 클래스를 메모리에 로드하므로 static initializer block이 실행된다.")
    void executeStaticInitializerBlockOnce() throws Exception {
        // given

        // when
        new InitializerBlock();

        // then
    }

    @Test
    @DisplayName("인스턴스를 생성할 때마다 instance initializer block을 실행한다.")
    void executeInitializerBlockEach() throws Exception {
        // given

        // when
        new InitializerBlock();
        new InitializerBlock();
        new InitializerBlock();

        // then
    }

    @Test
    @DisplayName("생성자보다 instance initializer block이 먼저 실행된다.")
    void priorityBetweenConstructorAndInitializerBlock() throws Exception {
        // given

        // when
        new InitializerBlock("first instance");
        new InitializerBlock("second instance");
        new InitializerBlock("third instance");

        // then
    }
}
