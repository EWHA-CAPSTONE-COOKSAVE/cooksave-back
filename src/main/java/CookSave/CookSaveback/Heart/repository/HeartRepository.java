package CookSave.CookSaveback.Heart.repository;

import CookSave.CookSaveback.Heart.domain.Heart;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Recipe.domain.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HeartRepository extends JpaRepository<Heart, Long> {
    boolean existsByMemberAndRecipe(Member member, Recipe recipe);
    Optional<Heart> findByMemberAndRecipe(Member member, Recipe recipe);
    List<Heart> findAllByMember(Member member);
}
