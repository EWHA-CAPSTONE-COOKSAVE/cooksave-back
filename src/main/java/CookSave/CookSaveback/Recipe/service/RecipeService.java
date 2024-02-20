package CookSave.CookSaveback.Recipe.service;

import CookSave.CookSaveback.Heart.repository.HeartRepository;
import CookSave.CookSaveback.Ingredient.domain.Ingredient;
import CookSave.CookSaveback.Ingredient.dto.IngredientResponseDto;
import CookSave.CookSaveback.Ingredient.repository.IngredientRepository;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.service.MemberService;
import CookSave.CookSaveback.Recipe.domain.Recipe;
import CookSave.CookSaveback.Recipe.dto.RecipeDetailResponseDto;
import CookSave.CookSaveback.Recipe.dto.RecipeResponseDto;
import CookSave.CookSaveback.Recipe.repository.RecipeRepository;
import CookSave.CookSaveback.RecipeTag.repository.RecipeTagRepository;
import CookSave.CookSaveback.Tag.domain.Tag;
import CookSave.CookSaveback.Tag.repository.TagRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;
    private final RecipeTagRepository recipeTagRepository;
    private final HeartRepository heartRepository;
    private final TagRepository tagRepository;

    // 전체 레시피 조회
    public List<RecipeResponseDto> getRecipeList(Member member) {
        List<RecipeResponseDto> recipeList = new ArrayList<>();

        List<Recipe> recipes = new ArrayList<>();
        recipes = recipeRepository.findAll();
        int recipeCount = recipes.size();

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients = ingredientRepository.findAllByMember(member);

        List<Integer> tags = new ArrayList<>();
        for (Ingredient ingredient : ingredients) {
            tags.add(ingredient.getTag().getTagId());
            tags = tags.stream().distinct().collect(Collectors.toList());
        }
        int tagCount = tags.size();

        List<Integer> recipeTagCount = new ArrayList<>();

        for (int i = 0; i < recipeCount; i++) {
            recipeTagCount.add(0);
        }

        for (int i = 0; i < recipeCount; i++) {
            for (Integer tagId : tags) {
                Tag tag = tagRepository.findById(tagId)
                        .orElseThrow(() -> new EntityNotFoundException("해당되는 태그가 존재하지 않습니다."));
                if (recipeTagRepository.existsByRecipeAndTag(recipes.get(i), tag)) {
                    Integer currentCount = recipeTagCount.get(i);
                    Integer newCount = currentCount == null ? 0 : currentCount + 1;
                    recipeTagCount.set(i, newCount);
                }
            }
        }

        List<RecipeTagSort> sortedRecipeList = new ArrayList<>();
        for (int i = 0; i < recipeCount; i++) {
            sortedRecipeList.add(new RecipeTagSort(recipes.get(i), recipeTagCount.get(i)));
        }

        Comparator<RecipeTagSort> recipeComparator = Comparator.comparingInt(RecipeTagSort::getTagCount).reversed();
        sortedRecipeList.sort(recipeComparator);

        for (RecipeTagSort recipeTagSort : sortedRecipeList){
            Recipe recipe = recipeTagSort.getRecipe();
            boolean heart = heartRepository.existsByMemberAndRecipe(member, recipe);
            RecipeResponseDto recipeResponseDto = new RecipeResponseDto(recipe, heart);
            recipeList.add(recipeResponseDto);
        }
        return recipeList;
    }

    static class RecipeTagSort {
        private Recipe recipe;
        private int tagCount;

        public RecipeTagSort(Recipe recipe, int tagCount) {
            this.recipe = recipe;
            this.tagCount = tagCount;
        }

        public Recipe getRecipe() {
            return recipe;
        }

        public int getTagCount() {
            return tagCount;
        }
    }

    /*
    public RecipeDetailResponseDto getRecipeDetail(Long recipeId, Member member){

         Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new EntityNotFoundException("recipeId가 " + recipeId + "인 레시피가 존재하지 않습니다."));

        List<Ingredient> memberIngredients = ingredientRepository.findAllByMember(member);

        for (Ingredient ingredient : memberIngredients){
            Tag tag = tagRepository.findById(ingredient.getTag().getTagId())
                    .orElseThrow(() -> new EntityNotFoundException("tagId" + ingredient.getTag().getTagId() + "인 태그가 존재하지 않습니다."));
            if (recipeTagRepository.existsByRecipeAndTag(recipe, tag)){

            }
        }

        RecipeDetailResponseDto detailResponseDto = new RecipeDetailResponseDto(recipe, ingredients);
    }
     */
}
