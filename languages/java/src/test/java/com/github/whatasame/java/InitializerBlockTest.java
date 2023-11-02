package com.github.whatasame.java;

import static org.assertj.core.api.Assertions.assertThatCode;

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
    @DisplayName("클래스를 사용하지 않으면 JVM이 클래스 로더에게 클래스 로드 요청을 보내지 않기 때문에 static initializer block은 실행되지 않는다.")
    void doesNotExecuteInitializerBlock() throws Exception {
        /* given */

        /* when */

        /* then */
    }

    @Test
    @Order(2)
    @DisplayName("클래스 로더로 클래스를 불러오면 static initializer block이 실행된다.")
    void executeStaticInitializerBlockOnce() throws Exception {
        /* given */
        final String name = "com.github.whatasame.java.basic.InitializerBlock";

        /* when & then */
        assertThatCode(() -> Class.forName(name))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("인스턴스를 생성할 때마다 instance initializer block을 실행한다.")
    void executeInitializerBlockEach() throws Exception {
        /* given */

        /* when */
        new InitializerBlock();
        new InitializerBlock();
        new InitializerBlock();

        /* then */
        // 콘솔 출력을 확인하여 instance initializer block이 3회 실행되는 것을 확인한다.
    }

    @Test
    @DisplayName("생성자보다 instance initializer block이 먼저 실행된다.")
    void priorityBetweenConstructorAndInitializerBlock() throws Exception {
        /* given */

        /* when */
        new InitializerBlock("first instance");
        new InitializerBlock("second instance");
        new InitializerBlock("third instance");

        /* then */
        // 콘솔 출력을 확인하여 instance initializer block이 생성자보다 먼저 실행되는 것을 확인한다.
    }
}
