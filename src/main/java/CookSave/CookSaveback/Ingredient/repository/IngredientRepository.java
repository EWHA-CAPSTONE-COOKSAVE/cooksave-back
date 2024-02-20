package CookSave.CookSaveback.Ingredient.repository;

import CookSave.CookSaveback.Ingredient.domain.Ingredient;

import CookSave.CookSaveback.Member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByMember(Member member);
    Long countAllByMember(Member member);
}
