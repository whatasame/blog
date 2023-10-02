package com.github.whatasame.springdatajpa.mouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface MouseRepository extends JpaRepository<Mouse, Long> {

    @Modifying
    @Transactional
    @Query("update Mouse m set m.name = :name where m.id = :id")
    void updateNameById(@Param("id") final Long id, @Param("name") final String name);
}
