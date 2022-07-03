package study.datajpa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {

    List<Member> findByUsername(String username);
    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);
    List<Member> findTop3AllByOrderByAge();

    //@Query, 리포지토리 메소드에 쿼리 정의하기
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<Member> findUsernameList();
    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name)" +
            " from Member m join m.team t")
    List<MemberDto> findMemberDto();

    List<Member> findListByUsername(String username);
    Member findMemberByUsername(String username);
    Optional<Member> findOptionalByUsername(String username);

    @Query(value = "select m from Member m left join m.team t",
    countQuery = "select count(m) from Member m")
    Page<Member> findByAge(int age, Pageable pageable);

    @Modifying(clearAutomatically = true) //이게 있어야 값 수정이 됨, 자동클리어
    @Query("update Member m set m.age = m.age + 1 where m.age>= :age")
    int bulkAgePlus(@Param("age") int age);


    @Query("select m from Member m join fetch m.team")
    List<Member> findMemberFetchJoin();

    //entitygraph를 쓰면 위 jpql과 같이 적용된다.
    @Override
    @EntityGraph(attributePaths = "team")
    List<Member> findAll();

    @EntityGraph(attributePaths ={"team"})
    List<Member> findEntityGraphByUsername(@Param("username") String username);
}
