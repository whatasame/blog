package com.github.whatasame.springdatajpamysql.dao;

import com.github.whatasame.springdatajpamysql.entity.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MouseRepository extends JpaRepository<Mouse, Long> {

    @Modifying
    @Transactional
    @Query("update Mouse m set m.name = :name where m.id = :id")
    void updateNameById(final Long id, final String name);
}
