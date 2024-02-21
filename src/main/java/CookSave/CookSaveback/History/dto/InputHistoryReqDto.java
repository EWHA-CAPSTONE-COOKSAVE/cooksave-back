package CookSave.CookSaveback.History.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class InputHistoryReqDto {
    private String name;  // 사용자 지정 요리명
    private Integer total;
    private List<HistoryIngredientReqDto> ingredients;
}
