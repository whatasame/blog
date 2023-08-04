package com.github.whatasame.springdatajpa.embeddable.repository;

import com.github.whatasame.springdatajpa.embeddable.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

}
