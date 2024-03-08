package CookSave.CookSaveback.History.dto;

import CookSave.CookSaveback.History.domain.History;
import CookSave.CookSaveback.HistoryIngredient.domain.HistoryIngredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HistoryDetailResDto {
    private Integer total;
    private List<HistoryIngredientResDto> ingredients;

    @Builder
    public HistoryDetailResDto(History history, List<HistoryIngredientResDto> ingredients){
        this.total = history.getTotal();
        this.ingredients = ingredients;
    }
}
