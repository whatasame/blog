package com.github.whatasame.springdatajpa.attributeconverter.repository;

import com.github.whatasame.springdatajpa.attributeconverter.entity.Member;
import com.github.whatasame.springdatajpa.attributeconverter.entity.MemberName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(final MemberName name);
}
