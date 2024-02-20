package CookSave.CookSaveback.Heart.repository;

import CookSave.CookSaveback.Heart.domain.Heart;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    boolean existsByMemberAndRecipe(Member member, Recipe recipe);
}
