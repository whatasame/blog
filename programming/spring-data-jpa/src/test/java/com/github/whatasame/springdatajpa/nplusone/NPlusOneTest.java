package com.github.whatasame.springdatajpa.nplusone;

import com.github.whatasame.springdatajpa.nplusone.domain.Member;
import com.github.whatasame.springdatajpa.nplusone.domain.Team;
import com.github.whatasame.springdatajpa.nplusone.repository.MemberRepository;
import com.github.whatasame.springdatajpa.nplusone.repository.TeamRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.IntStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DisplayName("N + 1 학습 테스트")
@DataJpaTest(properties = "spring.config.location=classpath:/nplusone/application.yml")
class NPlusOneTest {

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    EntityManager entityManager;

    @BeforeEach
    @DisplayName("1~3번 팀에 사람 30명이 균등하게 가입한다.")
    void setUp() {
        System.out.println("<============== Before Each START==============>");
        final List<Team> teams = IntStream.rangeClosed(1, 3)
            .mapToObj(num -> new Team(num + "번 팀"))
            .toList();

        final List<Member> members = IntStream.rangeClosed(1, 9)
            .mapToObj(num -> new Member("사람 " + num))
            .toList();

        for (int i = 0; i < members.size(); i++) {
            teams.get(i / teams.size()).addMember(members.get(i));
        }

        teamRepository.saveAll(teams);
        entityManager.flush();
        entityManager.clear();
        System.out.println("<============== Before Each END ==============>");
    }

    @Test
    @DisplayName("[N + 1 발생] Member 조회 시 team 정보를 가져오지 않아 team을 조회할 때 각각 쿼리가 필요하다.")
    void test_01() {
        /* given */
        final List<Member> members = memberRepository.findAll();

        /* when */
        for (Member member : members) {
            System.out.println(member.getName() + "의 팀은 " + member.getTeam().getName() + "입니다.");
        }

        /* then */
        System.out.println(">>>>> Result: Member query 1개, team query 3개");
    }

    @Test
    @DisplayName("[N + 1 발생] Team 조회 시 List<Member> 정보를 가져오지 않아 Member을 조회할 때 각각 쿼리가 필요하다.")
    void test_02() {
        /* given */
        final List<Team> teams = teamRepository.findAll();

        /* when */
        for (Team team : teams) {
            for (Member member : team.getMembers()) {
                System.out.println(team.getName() + "의 회원은 " + member.getName());
            }
        }

        /* then */
        System.out.println(">>>>> Result: Team query 1개, members query 3개");
    }

    @Test
    @DisplayName("[N + 1 해결] Member 조회 시 fetch join으로 Team 정보를 같이 가져온다.")
    void test_03() {
        /* given */
        final List<Member> members = memberRepository.findAllWithTeam();

        /* when */
        for (Member member : members) {
            System.out.println(member.getName() + "의 팀은 " + member.getTeam().getName() + "입니다.");
        }

        /* then */
        System.out.println(">>>>> Result: Member query 1개");
    }

    @Test
    @DisplayName("[N + 1 해결] Team 조회 시 fetch join으로 List<Member> 정보를 같이 가져온다.")
    void test_04() {
        /* given */
        final List<Team> teams = teamRepository.findAllWithMember();

        /* when */
        for (Team team : teams) {
            for (Member member : team.getMembers()) {
                System.out.println(team.getName() + "의 회원은 " + member.getName());
            }
        }

        /* then */
        System.out.println(">>>>> Result: Team query 1개");
    }
}
