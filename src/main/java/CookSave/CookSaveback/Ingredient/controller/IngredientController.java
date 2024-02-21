package CookSave.CookSaveback.Ingredient.controller;

import CookSave.CookSaveback.Ingredient.dto.IngredientRequestDto;
import CookSave.CookSaveback.Ingredient.dto.IngredientResponseDto;
import CookSave.CookSaveback.Ingredient.dto.SubtractRequestDto;
import CookSave.CookSaveback.Ingredient.dto.UpdateRequestDto;
import CookSave.CookSaveback.Ingredient.service.IngredientService;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class IngredientController {
    private final MemberService memberService;
    private final IngredientService ingredientService;

    // 보유한 재료 목록 조회
    @GetMapping("/ingredients/list")
    @ResponseStatus(value = HttpStatus.OK)
    public List<IngredientResponseDto> getIngredientList(){
        // 현재 로그인한 member 불러오기
        Member member = memberService.getLoginMember();
        return ingredientService.getIngredientList(member);
    }

    // 재료 직접 입력
    @PostMapping("/ingredients/typing")
    public ResponseEntity<String> createIngredientList(@RequestBody List<IngredientRequestDto> ingredientRequestDtoList){
        Member member = memberService.getLoginMember();
        ingredientService.createIngredients(member, ingredientRequestDtoList);
        return new ResponseEntity<>("재료가 등록되었습니다.", HttpStatus.CREATED);
    }

    // 재료 목록에서 iconId, amount 수정
    @PatchMapping("/ingredients/list")
    @ResponseStatus(value = HttpStatus.OK)
    public String updateIngredientList(@RequestBody List<UpdateRequestDto> updateRequestDtoList){
        Member member = memberService.getLoginMember();
        ingredientService.updateIngredients(member, updateRequestDtoList);
        return "재료가 수정되었습니다.";
    }

    // 재료 삭제
    @DeleteMapping("/ingredients/list/{ingredient_id}")
    @ResponseStatus(value=HttpStatus.OK)
    public String deleteIngredient(@PathVariable("ingredient_id") Long ingredientId){
        Member member = memberService.getLoginMember();
        ingredientService.deleteIngredient(member, ingredientId);
        return "재료가 삭제되었습니다.";
    }

    // 등록되어 있는 레시피 재료 차감
    @PatchMapping("/recipes/{recipe_id}/ingredients")
    @ResponseStatus(value = HttpStatus.OK)
    public String subtractIngredient(@PathVariable("recipe_id") Long recipeId, @RequestBody List<SubtractRequestDto> subtractDtoList){
        Member member = memberService.getLoginMember();
        ingredientService.subtractIngredient(member, subtractDtoList);
        return "재료가 차감되었습니다.";
    }

    // 사용자 입력 레시피 재료 차감
    @PatchMapping("/recipes/input/ingredients")
    @ResponseStatus(value = HttpStatus.OK)
    public String subtractIngredient(@RequestBody List<SubtractRequestDto> subtractDtoList){
        Member member = memberService.getLoginMember();
        ingredientService.subtractIngredient(member, subtractDtoList);
        return "재료가 차감되었습니다.";
    }
}
