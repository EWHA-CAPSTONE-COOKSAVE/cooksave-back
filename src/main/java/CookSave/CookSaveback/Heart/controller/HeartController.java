package CookSave.CookSaveback.Heart.controller;

import CookSave.CookSaveback.Heart.service.HeartService;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
