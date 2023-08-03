package com.github.whatasame.springdatajpa.attributeconverter;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.whatasame.springdatajpa.attributeconverter.entity.Member;
import com.github.whatasame.springdatajpa.attributeconverter.entity.MemberAge;
import com.github.whatasame.springdatajpa.attributeconverter.entity.MemberName;
import com.github.whatasame.springdatajpa.attributeconverter.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class EntityTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원을 저장하고 조회한다.")
    void createMemberAndFindMember() {
        /* given */
        final MemberName name = new MemberName("whatasame");
        final MemberAge age = new MemberAge(17);
        final Member member = new Member(name, age);

        /* when */
        memberRepository.save(member);

        /* then */
        assertThat(memberRepository.findByName(name)).isPresent();
    }
}
