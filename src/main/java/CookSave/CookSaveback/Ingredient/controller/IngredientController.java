package CookSave.CookSaveback.Ingredient.controller;

import CookSave.CookSaveback.Ingredient.dto.IngredientRequestDto;
import CookSave.CookSaveback.Ingredient.dto.IngredientResponseDto;
import CookSave.CookSaveback.Ingredient.dto.UpdateRequestDto;
import CookSave.CookSaveback.Ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    // 보유한 재료 목록 조회
    @GetMapping("/list")
    @ResponseStatus(value = HttpStatus.OK)
    public List<IngredientResponseDto> getIngredientList(){
        return ingredientService.getIngredientList();
    }

    // 재료 직접 입력
    @PostMapping("/typing")
    public ResponseEntity<String> createIngredientList(@RequestBody List<IngredientRequestDto> ingredientRequestDtoList){
        ingredientService.createIngredients(ingredientRequestDtoList);
        return new ResponseEntity<>("재료가 등록되었습니다.", HttpStatus.CREATED);
    }

    // 재료 목록에서 iconId, amount 수정
    @PatchMapping("/list")
    @ResponseStatus(value = HttpStatus.OK)
    public String updateIngredientList(@RequestBody List<UpdateRequestDto> updateRequestDtoList){
        ingredientService.updateIngredients(updateRequestDtoList);
        return "재료가 수정되었습니다.";
    }

    // 재료 삭제
    @DeleteMapping("/list/{ingredient_id}")
    @ResponseStatus(value=HttpStatus.OK)
    public String deleteIngredient(@PathVariable("ingredient_id") Long ingredientId){
        ingredientService.deleteIngredient(ingredientId);
        return "재료가 삭제되었습니다.";
    }
}
