package CookSave.CookSaveback.Ingredient.repository;

import CookSave.CookSaveback.Ingredient.domain.Ingredient;

import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByMember(Member member);
    Long countAllByMember(Member member);
    Optional<Ingredient> findByIngredientIdAndMember(Long ingredientId, Member member);
    boolean existsByMemberAndTag(Member member, Tag tag);
}
