package CookSave.CookSaveback.Recipe.controller;

import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.service.MemberService;
import CookSave.CookSaveback.Recipe.dto.RecipeDetailResponseDto;
import CookSave.CookSaveback.Recipe.dto.RecipeResponseDto;
import CookSave.CookSaveback.Recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {
    private final RecipeService recipeService;
    private final MemberService memberService;


    // 전체 레시피 목록 조회
    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public List<RecipeResponseDto> getRecipeList(){
        Member member = memberService.getLoginMember();
        return recipeService.getRecipeList(member);
    }

    // 레시피 상세 조회
    @GetMapping("/{recipe_id}")
    @ResponseStatus(value = HttpStatus.OK)
    public RecipeDetailResponseDto getRecipeDetail(@PathVariable("recipe_id") Long recipeId){
        Member member = memberService.getLoginMember();
        return recipeService.getRecipeDetail(recipeId, member);
    }
}
