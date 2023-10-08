package com.github.whatasame.springdataredis;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.whatasame.springdataredis.base.RedisBaseTest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("학습 테스트: Redisson 분산락")
class DistributionLockTest extends RedisBaseTest {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    RedissonClient redissonClient;

    @Test
    @DisplayName("Redisson 분산 락의 waitTime을 초과하면 false를 반환한다.")
    void exceedWaitTime() throws Exception {
        /* given */
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final RLock rLock = redissonClient.getLock("ROCK_IS_NOT_LOCK");
        final AtomicLong count = new AtomicLong(0L);

        final int WAIT_TIME = 1;
        final int LEASE_TIME = 777;

        /* when */
        executorService.execute(() -> {
            try {
                if (rLock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS)) {
                    log.info("[1번 쓰레드]: 락 획득");

                    count.incrementAndGet();
                    Thread.sleep(2000);
                } else {
                    log.info("[1번 쓰레드]: 락 획득 실패");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.execute(() -> {
            try {
                if (rLock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS)) {
                    log.info("[2번 쓰레드]: 락 획득");

                    count.incrementAndGet();
                    Thread.sleep(2000);
                } else {
                    log.info("[2번 쓰레드]: 락 획득 실패");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        /* then */
        assertThat(count.get()).isEqualTo(1L);
    }

    @Test
    @DisplayName("Redisson 분산 락의 leaseTime을 초과하면 분산 락을 해제한다.")
    void exceedLeaseTime() throws Exception {
        /* given */
        final ExecutorService executorService = Executors.newCachedThreadPool();
        final RLock rLock = redissonClient.getLock("ROCK_IS_NOT_LOCK");
        final AtomicLong count = new AtomicLong(0L);

        final int WAIT_TIME = 777;
        final int LEASE_TIME = 1;

        /* when */
        executorService.execute(() -> {
            try {
                if (rLock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS)) {
                    log.info("[1번 쓰레드]: 락 획득");

                    count.incrementAndGet();
                    Thread.sleep(2000);
                } else {
                    log.info("[1번 쓰레드]: 락 획득 실패");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.execute(() -> {
            try {
                if (rLock.tryLock(WAIT_TIME, LEASE_TIME, TimeUnit.SECONDS)) {
                    log.info("[2번 쓰레드]: 락 획득");

                    count.incrementAndGet();
                    Thread.sleep(2000);
                } else {
                    log.info("[2번 쓰레드]: 락 획득 실패");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.HOURS);

        /* then */
        assertThat(count.get()).isEqualTo(2L);
    }
}
