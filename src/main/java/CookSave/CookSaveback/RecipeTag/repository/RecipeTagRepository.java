package CookSave.CookSaveback.RecipeTag.repository;

import CookSave.CookSaveback.Recipe.domain.Recipe;
import CookSave.CookSaveback.RecipeTag.domain.RecipeTag;
import CookSave.CookSaveback.Tag.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecipeTagRepository extends JpaRepository<RecipeTag, Long> {
    boolean existsByRecipeAndTag(Recipe recipe, Tag tag);
}
