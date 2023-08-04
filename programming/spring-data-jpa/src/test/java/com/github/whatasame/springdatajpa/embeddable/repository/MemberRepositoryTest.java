package com.github.whatasame.springdatajpa.embeddable.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.github.whatasame.springdatajpa.embeddable.entity.Email;
import com.github.whatasame.springdatajpa.embeddable.entity.Member;
import com.github.whatasame.springdatajpa.embeddable.entity.MemberName;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("회원을 저장하고 검색할 수 있다.")
    void createMemberAndFindMember() {
        /* given */
        final Member member = new Member(
            new MemberName("라탕", "마"),
            new Email("maratang@gmail.com"),
            new Email("maratang@naver.com")
        );

        /* when */
        final Member result = memberRepository.save(member);

        /* then */
        final Optional<Member> found = memberRepository.findById(result.getId());
        assertThat(found).isPresent();
    }
}
