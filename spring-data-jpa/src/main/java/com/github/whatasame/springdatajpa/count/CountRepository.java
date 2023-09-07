package com.github.whatasame.springdatajpa.count;

import jakarta.persistence.LockModeType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CountRepository extends JpaRepository<Count, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("select c from Count c where c.id = :id")
    Optional<Count> findByIdWithPessimisticRead(@Param("id") final Long id);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select c from Count c where c.id = :id")
    Optional<Count> findByIdWithPessimisticWrite(@Param("id") final Long id);
}
