package CookSave.CookSaveback.HistoryIngredient.repository;

import CookSave.CookSaveback.HistoryIngredient.domain.HistoryIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoryIngredientRepository extends JpaRepository<HistoryIngredient, Long> {
}
