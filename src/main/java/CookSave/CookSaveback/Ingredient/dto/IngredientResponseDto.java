package CookSave.CookSaveback.Ingredient.dto;

import CookSave.CookSaveback.Ingredient.domain.Ingredient;
import lombok.*;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class IngredientResponseDto {
    private Long ingredientId;
    private Integer iconId;
    private String name;
    private Integer price;
    private Float amount;
    private LocalDate createdAt;

    @Builder
    public IngredientResponseDto(Ingredient ingredient){
        this.ingredientId = ingredient.getIngredientId();
        this.iconId = ingredient.getIcon().getIconId();
        this.name = ingredient.getName();
        this.price = ingredient.getPrice();
        this.amount = ingredient.getAmount();
        this.createdAt = ingredient.getCreatedAt();
    }
}
