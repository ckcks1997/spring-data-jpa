package study.datajpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.entity.Member;
import study.datajpa.repository.MemberJpaRepository;

@SpringBootTest
class DataJpaApplicationTests {

	@Autowired
	MemberJpaRepository memberJpaRepository;

	@Test
	@Transactional
	void contextLoads() {
		Member member = new Member("membera");
		Member savedMember = memberJpaRepository.save(member);

		Member findMember = memberJpaRepository.find(savedMember.getId());

		Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
	}

}
