package CookSave.CookSaveback.Icon.repository;

import CookSave.CookSaveback.Icon.domain.Icon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IconRepository extends JpaRepository<Icon, Integer> {
}
