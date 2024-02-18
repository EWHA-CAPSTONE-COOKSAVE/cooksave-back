package CookSave.CookSaveback.Ingredient.repository;

import CookSave.CookSaveback.Ingredient.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
}
