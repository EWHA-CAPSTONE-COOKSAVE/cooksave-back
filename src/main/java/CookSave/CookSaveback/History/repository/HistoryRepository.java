package CookSave.CookSaveback.History.repository;


import CookSave.CookSaveback.History.domain.History;
import CookSave.CookSaveback.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    Optional<History> findByHistoryIdAndMember(Long historyId, Member member);
}
