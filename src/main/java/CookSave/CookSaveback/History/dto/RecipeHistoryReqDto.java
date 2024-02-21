package CookSave.CookSaveback.History.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RecipeHistoryReqDto {
    private Integer total;
    private List<HistoryIngredientReqDto> ingredients;
}


