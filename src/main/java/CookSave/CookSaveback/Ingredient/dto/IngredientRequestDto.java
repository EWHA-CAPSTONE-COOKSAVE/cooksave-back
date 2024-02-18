package CookSave.CookSaveback.Ingredient.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class IngredientRequestDto {
    private Integer iconId;
    private String name;
    private Integer price;
    private Float amount;
}
