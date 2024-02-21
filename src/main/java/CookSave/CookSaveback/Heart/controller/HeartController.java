package CookSave.CookSaveback.Heart.controller;

import CookSave.CookSaveback.Heart.dto.HeartRecipeDto;
import CookSave.CookSaveback.Heart.service.HeartService;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class HeartController {
    private final MemberService memberService;
    private final HeartService heartService;

    // 레시피 찜 등록
    @PostMapping("/{recipe_id}/hearts")
    public ResponseEntity<String> heartRecipe(@PathVariable("recipe_id") Long recipeId){
        Member member = memberService.getLoginMember();
        String response = heartService.heartRecipe(member, recipeId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // 레시피 찜 취소
    @DeleteMapping("/{recipe_id}/hearts")
    public ResponseEntity<String> cancelRecipeHeart(@PathVariable("recipe_id") Long recipeId){
        Member member = memberService.getLoginMember();
        String response = heartService.cancelRecipeHeart(member, recipeId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 찜한 레시피 목록 조회
    @GetMapping("/saved")
    @ResponseStatus(value = HttpStatus.OK)
    public List<HeartRecipeDto> getHeartRecipeList(){
        Member member = memberService.getLoginMember();
        return heartService.getHeartRecipeList(member);
    }
}
