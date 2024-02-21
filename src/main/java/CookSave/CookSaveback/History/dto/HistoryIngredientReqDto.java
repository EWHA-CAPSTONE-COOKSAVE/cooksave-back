package CookSave.CookSaveback.History.dto;

import lombok.Getter;

@Getter
public class HistoryIngredientReqDto {
    private String name;
    private Float amount;
    private Integer price;  // 수량이 반영된 가격
}
