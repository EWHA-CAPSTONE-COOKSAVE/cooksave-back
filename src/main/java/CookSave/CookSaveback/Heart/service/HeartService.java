package CookSave.CookSaveback.Heart.service;

import CookSave.CookSaveback.Heart.domain.Heart;
import CookSave.CookSaveback.Heart.dto.HeartRecipeDto;
import CookSave.CookSaveback.Heart.repository.HeartRepository;
import CookSave.CookSaveback.Ingredient.domain.Ingredient;
import CookSave.CookSaveback.Ingredient.repository.IngredientRepository;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Recipe.domain.Recipe;
import CookSave.CookSaveback.Recipe.repository.RecipeRepository;
import CookSave.CookSaveback.RecipeTag.domain.RecipeTag;
import CookSave.CookSaveback.RecipeTag.repository.RecipeTagRepository;
import CookSave.CookSaveback.Tag.domain.Tag;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HeartService {
    private final HeartRepository heartRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeTagRepository recipeTagRepository;

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

    @Transactional
    public String cancelRecipeHeart(Member member, Long recipeId){
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("recipeId가 " + recipeId + "인 레시피가 없습니다."));
        Heart heart = heartRepository.findByMemberAndRecipe(member, recipe)
                .orElseThrow(() -> new EntityNotFoundException("해당 레시피는 찜 목록에 존재하지 않습니다."));
        heartRepository.delete(heart);
        return "recipeId가 " + recipeId + "인 레시피가 찜 목록에서 제거되었습니다.";
    }

    public List<HeartRecipeDto> getHeartRecipeList(Member member){
        List<HeartRecipeDto> heartRecipeDtoList = new ArrayList<>();

        List<Heart> heartList = heartRepository.findAllByMember(member);
        List<Ingredient> ingredientList = ingredientRepository.findAllByMember(member);
        List<Tag> ingredientTagList = new ArrayList<>();

        for(Ingredient ingredient : ingredientList){
            ingredientTagList.add(ingredient.getTag());
        }

        for(Heart heart : heartList){
            List<String> tags = new ArrayList<>();
            Recipe recipe = heart.getRecipe();
            List<RecipeTag> recipeTagList = recipeTagRepository.findAllByRecipe(recipe);

            List<Tag> tagList = new ArrayList<>();
            for(RecipeTag recipeTag : recipeTagList){
                tagList.add(recipeTag.getTag());
            }

            for(Tag tag : tagList){
                if(ingredientTagList.contains(tag)){
                    tags.add(tag.getName());
                }
            }
            heartRecipeDtoList.add(new HeartRecipeDto(heart, tags));
        }
        return heartRecipeDtoList;
    }
}
