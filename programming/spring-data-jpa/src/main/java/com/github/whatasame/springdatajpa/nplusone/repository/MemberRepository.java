package com.github.whatasame.springdatajpa.nplusone.repository;

import com.github.whatasame.springdatajpa.nplusone.domain.Member;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m from Member m join fetch m.team")
    List<Member> findAllWithTeam();
}
