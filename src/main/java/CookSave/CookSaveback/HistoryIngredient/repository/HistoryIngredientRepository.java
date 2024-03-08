package CookSave.CookSaveback.HistoryIngredient.repository;

import CookSave.CookSaveback.History.domain.History;
import CookSave.CookSaveback.HistoryIngredient.domain.HistoryIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryIngredientRepository extends JpaRepository<HistoryIngredient, Long> {
    List<HistoryIngredient> findAllByHistory(History history);
}
