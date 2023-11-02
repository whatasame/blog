package com.github.whatasame.springdatajpa.mysql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import com.github.whatasame.springdatajpa.instagram.Post;
import com.github.whatasame.springdatajpa.instagram.PostAutoIncr;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

/**
 * Hibernate가 프로시저 생성 중간에 포함된 ;에서 SQL문을 끊어서 syntax 에러가 발생.
 * <p>
 * 이를 해결하기 위해 "procedure.sql"의 일반 SQL의 구분자를 ^;로 변경
 */
@DisplayName("학습 테스트: Instagram ID generator")
@MySqlTest
@Sql("/mysql/sequence.sql")
@Sql(
    scripts = "/mysql/procedure.sql",
    config = @SqlConfig(separator = "^;")
)
class InstagramIdGeneratorTest {

    private static final LocalDateTime SERVICE_START_DATE_TIME = LocalDateTime.of(2023, 1, 1, 0, 0);
    private static final long SERVICE_START_MILLI = 1672531200000L; // 2023-01-01 00:00:00
    private static final int LOGICAL_SHARD_MAX_COUNT = 1024;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @DisplayName("PK를 시간 + 샤드 + 시퀀스의 조합으로 생성한다.")
    void generateInstagramId() {
        /* given */
        final long userId = 1801;
        final Post post = new Post(userId);

        /* when */
        entityManager.persist(post);

        /* then */
        final Long id = post.getId();
        final LocalDateTime createdAt = extractLocalTimeDate(id);
        assertAll(
            () -> assertThat(createdAt.isAfter(SERVICE_START_DATE_TIME)).isTrue(),
            () -> assertThat(createdAt.isBefore(LocalDateTime.now())).isTrue(),
            () -> assertThat(extractShardId(id)).isEqualTo(userId % LOGICAL_SHARD_MAX_COUNT),
            () -> assertThat(extractSequence(id)).isEqualTo(1L)
        );
        System.out.println("Generated id = " + id);
    }

    @Test
    @DisplayName("AUTO_INCREMENT를 사용할 때와 속도 비교")
    void comparisonSpeed() {
        /* given */
        final long userId = 1801L;
        final int totalSize = 100;

        /* when */
        final long autoIncrStart = System.currentTimeMillis();
        for (int i = 0; i < totalSize; i++) {
            final PostAutoIncr post = new PostAutoIncr(userId);
            entityManager.persist(post);
            entityManager.flush();
        }
        final long autoIncrEnd = System.currentTimeMillis();

        final long instaIdGenStart = System.currentTimeMillis();
        for (int i = 0; i < totalSize; i++) {
            final Post post = new Post(userId);
            entityManager.persist(post);
            entityManager.flush();
        }
        final long instaIdGenEnd = System.currentTimeMillis();

        /* then */
        System.out.println("Elapsed instaIdGen time = " + (instaIdGenEnd - instaIdGenStart) + "ms");
        System.out.println("Elapsed autoIncr time = " + (autoIncrEnd - autoIncrStart) + "ms");
    }

    private LocalDateTime extractLocalTimeDate(final long id) {
        final long milliseconds = id >> 20 & 0x1FFFFFFFFFFL;

        return Instant.ofEpochMilli(SERVICE_START_MILLI + milliseconds)
            .atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    private Long extractShardId(final long id) {
        return id >> 10 & 0x3FFL;
    }

    private Long extractSequence(final long id) {
        return id & 0x3FFL;
    }
}
