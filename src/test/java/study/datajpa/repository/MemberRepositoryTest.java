package study.datajpa.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;


    @Test
    public void testMember(){
        Member member = new Member("membera");
        Member saved = memberRepository.save(member);

        Optional<Member> byId = memberRepository.findById(saved.getId());
        Member member1 = byId.get();

        Assertions.assertThat(saved.getId()).isEqualTo(member1.getId());
    }
}