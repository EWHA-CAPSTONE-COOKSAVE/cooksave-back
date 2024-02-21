package CookSave.CookSaveback.History.controller;

import CookSave.CookSaveback.History.dto.BudgetRequestDto;
import CookSave.CookSaveback.History.dto.RecipeHistoryReqDto;
import CookSave.CookSaveback.History.service.HistoryService;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HistoryController {
    private final MemberService memberService;
    private final HistoryService historyService;

    // 예산 설정
    @PatchMapping("members/budget")
    public ResponseEntity<String> updateBudget(@RequestBody BudgetRequestDto budgetRequestDto){
        Member member = memberService.getLoginMember();
        historyService.updateBudget(member, budgetRequestDto);
        return new ResponseEntity<>("예산이 설정되었습니다.", HttpStatus.OK);
    }

    // 레시피 요리 내역 저장
    @PostMapping("/recipes/{recipe_id}/ingredients")
    public ResponseEntity<String> createRecipeHistory(@PathVariable("recipe_id") Long recipeId, @RequestBody RecipeHistoryReqDto recipeHistoryReqDto){
        Member member = memberService.getLoginMember();
        historyService.createRecipeHistory(member, recipeId, recipeHistoryReqDto);
        return new ResponseEntity<>("요리 내역이 저장되었습니다.", HttpStatus.CREATED);
    }
}
