package CookSave.CookSaveback.Ingredient.controller;

import CookSave.CookSaveback.Ingredient.dto.IngredientRequestDto;
import CookSave.CookSaveback.Ingredient.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/ingredients")
public class IngredientController {
    private final IngredientService ingredientService;

    @PostMapping("/typing")
    public ResponseEntity<String> createIngredientList(@RequestBody List<IngredientRequestDto> ingredientRequestDtoList){
        ingredientService.createIngredients(ingredientRequestDtoList);
        return new ResponseEntity<>("재료가 등록되었습니다.", HttpStatus.CREATED);
    }
}
