package com.github.whatasame.springdatajpa;

import static org.assertj.core.api.Assertions.assertThatCode;

import com.github.whatasame.springdatajpa.dummy.DummyInnerService;
import com.github.whatasame.springdatajpa.dummy.DummyService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.UnexpectedRollbackException;

/**
 * application.yml에서 logging.level.org.springframework.orm를 debug로 설정해야 트랜잭션 로그를 볼 수 있다.
 */
@SpringBootTest
@DisplayName("학습 테스트: 트랜잭션 롤백 테스트")
class RollbackTest {

    @Autowired
    DummyService dummyService;

    @Autowired
    DummyInnerService dummyInnerService;

    /**
     * "Initiating transaction rollback"라는 키워드가 포함된 로그가 1개 출력되는지 확인한다.
     */
    @Test
    @DisplayName("메서드가 unchecked exception을 던지는 경우 트랜잭션을 롤백한다.")
    void rollbackWithUncheckedException() throws Exception {
        /* given */


        /* when & then */
        assertThatCode(() -> dummyInnerService.throwRuntimeException())
            .isExactlyInstanceOf(RuntimeException.class);
    }

    /**
     * "Creating new transaction", "Initiating transaction rollback"라는 키워드가 포함된 로그가 1개씩 출력되는지 확인한다.
     */
    @Test
    @DisplayName("nested transactional에서 트랜잭션이 하나 생성된다.")
    void nestedTransactionalWithDefaultPropagation() throws Exception {
        /* given */


        /* when & then */
        assertThatCode(() -> dummyService.doesNotCatchRuntimeException())
            .isExactlyInstanceOf(RuntimeException.class);
    }

    /**
     * 최하단 Rollback exception 확인용의 메서드를 주석 해제하고 롤백 예외가 발생하는지 확인한다.
     */
    @Test
    @DisplayName("[전파 속성: REQUIRED] 내부 트랜잭션의 롤백이 외부 트랜잭션에 영향을 준다.")
    void rollbackEvenIfInnerExceptionIsCaught() throws Exception {
        /* given */


        /* when & then */
        assertThatCode(() -> dummyService.catchRuntimeException())
            .isExactlyInstanceOf(UnexpectedRollbackException.class);

        /* Rollback exception 확인용 */
//        dummyService.catchRuntimeException();
    }

    /**
     * "Suspending current transaction, creating new transaction"라는 키워드가 포함된 로그가 1개 출력되는지 확인한다.
     */
    @Test
    @DisplayName("[전파 속성: REQUIRES_NEW] 내부 트랜잭션의 롤백이 외부 트랜잭션에 영향을 준다.")
    void commitIfInnerExceptionIsCaught() throws Exception {
        /* given */

        /* when & then */
        assertThatCode(() -> dummyService.catchRuntimeExceptionWithPropagationRequiresNew())
            .doesNotThrowAnyException();
    }
}
