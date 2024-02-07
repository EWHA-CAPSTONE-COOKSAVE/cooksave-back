package CookSave.CookSaveback.Member.repository;

import CookSave.CookSaveback.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 아이디 중복검사를 위한 메서드
    Boolean existsByCooksaveId(String cooksaveId);

    Optional<Member> findByCooksaveId(String cooksaveId);
}
