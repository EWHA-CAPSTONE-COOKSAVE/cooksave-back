package CookSave.CookSaveback.Ingredient.dto;

import lombok.Getter;

@Getter
public class UpdateRequestDto {
    private Long ingredientId;
    private Integer iconId;
    private Float amount;
}
