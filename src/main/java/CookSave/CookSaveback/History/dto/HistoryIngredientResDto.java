package CookSave.CookSaveback.History.dto;

import CookSave.CookSaveback.HistoryIngredient.domain.HistoryIngredient;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HistoryIngredientResDto {
    private Long historyIngId;
    private String name;
    private Float amount;
    private Integer price;

    @Builder
    public HistoryIngredientResDto(HistoryIngredient historyIngredient){
        this.historyIngId = historyIngredient.getHistoryIngId();
        this.name = historyIngredient.getName();
        this.amount = historyIngredient.getAmount();
        this.price = historyIngredient.getPrice();
    }
}
