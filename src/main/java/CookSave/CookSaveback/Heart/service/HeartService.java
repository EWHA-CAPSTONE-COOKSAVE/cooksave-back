package CookSave.CookSaveback.Heart.service;

import CookSave.CookSaveback.Heart.domain.Heart;
import CookSave.CookSaveback.Heart.repository.HeartRepository;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Recipe.domain.Recipe;
import CookSave.CookSaveback.Recipe.repository.RecipeRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public String heartRecipe(Member member, Long recipeId){
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("recipeId가 " + recipeId + "인 레시피가 없습니다."));
        if(heartRepository.existsByMemberAndRecipe(member, recipe)){
            throw new RuntimeException("이미 저장 목록에 추가된 레시피입니다.");
        }
        else{
            Heart heart = new Heart(member, recipe);
            heartRepository.save(heart);
            return "recipeId가 " + recipeId + "인 레시피가 찜 목록에 추가되었습니다.";
        }
    }


}
