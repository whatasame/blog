package com.github.whatasame.springdatajpa;

import com.github.whatasame.springdatajpa.batchsize.Owner;
import com.github.whatasame.springdatajpa.batchsize.Restaurant;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.hibernate.Hibernate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

@DisplayName("학습 테스트: BatchSize")
@DataJpaTest
@Sql("/batch-size.sql")
class BatchSizeTest {

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("클래스에 @BatchSize를 달면 프록시를 초기화할 때 초기화되지 않은 다른 프록시도 함께 초기화한다.")
    void batchSizeOnClass() {
        /* given */
        final Owner proxy1 = entityManager.getReference(Owner.class, 1L);
        final Owner proxy2 = entityManager.getReference(Owner.class, 2L);
        final Owner proxy3 = entityManager.getReference(Owner.class, 3L);

        /* when */
        Hibernate.initialize(proxy1);

        /* then */
        // 로그의 WHERE ARRAY_CONTAINS() 쿼리에 1뿐만 아니라 2, 3도 포함되는 것을 확인한다.
    }

    @Test
    @DisplayName("컬렉션에 @BatchSize를 달면 PersistentCollection를 초기화할 때 초기화되지 않은 PersistentCollection도 함께 초기화한다.")
    void batchSizeOnCollection() {
        /* given */
        final Restaurant restaurant1 = entityManager.find(Restaurant.class, 1L);
        final Restaurant restaurant2 = entityManager.find(Restaurant.class, 2L);
        final Restaurant restaurant3 = entityManager.find(Restaurant.class, 3L);

        /* when */
        Hibernate.initialize(restaurant1.getMenus());

        /* then */
        // 로그의 WHERE ARRAY_CONTAINS() 쿼리에 1뿐만 아니라 2, 3도 포함되는 것을 확인한다.
    }
}
