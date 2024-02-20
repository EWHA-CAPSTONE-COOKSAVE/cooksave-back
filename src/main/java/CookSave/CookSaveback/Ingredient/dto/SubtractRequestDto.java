package CookSave.CookSaveback.Ingredient.dto;

import lombok.Getter;

@Getter
public class SubtractRequestDto {
    private Long ingredientId;
    private Float amount;
}
