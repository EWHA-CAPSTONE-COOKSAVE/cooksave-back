package CookSave.CookSaveback.Heart.dto;

import CookSave.CookSaveback.Heart.domain.Heart;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HeartRecipeDto {
    private Long heartId;
    private Long memberId;
    private Long recipeId;
    private String name;
    private String image;
    private List<String> tags;

    @Builder
    public HeartRecipeDto(Heart heart, List<String> tags){
        this.heartId = heart.getHeartId();
        this.memberId = heart.getMember().getMemberId();
        this.recipeId = heart.getRecipe().getRecipeId();
        this.name = heart.getRecipe().getName();
        this.image = heart.getRecipe().getImage();
        this.tags = tags;
    }
}
