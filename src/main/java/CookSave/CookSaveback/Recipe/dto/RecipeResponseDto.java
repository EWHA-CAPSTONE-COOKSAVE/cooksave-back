package CookSave.CookSaveback.Recipe.dto;

import CookSave.CookSaveback.Recipe.domain.Recipe;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecipeResponseDto {
    private Long recipeId;
    private String name;
    private String image;
    private String mainIng;
    private boolean heart;

    @Builder
    public RecipeResponseDto(Recipe recipe, boolean heart){
        this.recipeId = recipe.getRecipeId();
        this.name = recipe.getName();
        this.image = recipe.getImage();
        this.mainIng = recipe.getMainIng();
        this.heart = heart;
    }
}
