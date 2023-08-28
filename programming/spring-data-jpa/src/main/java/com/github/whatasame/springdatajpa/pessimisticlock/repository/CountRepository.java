package com.github.whatasame.springdatajpa.pessimisticlock.repository;

import com.github.whatasame.springdatajpa.pessimisticlock.entity.Count;
import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface CountRepository extends JpaRepository<Count, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select c from Count c where c.id = :id")
    Optional<Count> findByIdWithPessimisticRead(final Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Count c where c.id = :id")
    Optional<Count> findByIdWithPessimisticWrite(final Long id);

    @Lock(LockModeType.OPTIMISTIC)
    @Query("select c from Count c where c.id = :id")
    Optional<Count> findByIdWithOptimistic(final Long id);
}
