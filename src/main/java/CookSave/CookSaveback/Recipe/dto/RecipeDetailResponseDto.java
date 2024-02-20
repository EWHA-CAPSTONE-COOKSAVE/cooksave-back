package CookSave.CookSaveback.Recipe.dto;

import CookSave.CookSaveback.Ingredient.dto.IngredientResponseDto;
import CookSave.CookSaveback.Recipe.domain.Recipe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecipeDetailResponseDto {
    private String image;
    private String name;
    private String mainIng;
    private String content;
    private List<IngredientResponseDto> ingredients;
    private String video;

    @Builder
    public RecipeDetailResponseDto(Recipe recipe, List<IngredientResponseDto> ingredients){
        this.image = recipe.getImage();
        this.name = recipe.getName();
        this.mainIng = recipe.getMainIng();
        this.content = recipe.getContent();
        this.ingredients = ingredients;
        this.video = recipe.getVideo();
    }
}
