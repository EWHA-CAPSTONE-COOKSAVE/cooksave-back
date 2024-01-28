package CookSave.CookSaveback.Member.controller;

import CookSave.CookSaveback.Member.dto.LoginRequestDto;
import CookSave.CookSaveback.Member.dto.LoginResponseDto;
import CookSave.CookSaveback.Member.dto.SignUpRequestDto;
import CookSave.CookSaveback.Member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignUpRequestDto requestDto){
        return ResponseEntity.ok().body(memberService.signUp(requestDto.getCooksaveId(), requestDto.getPassword(), requestDto.getPasswordCheck()));
    }
    
    // 로그인
    // 성공적으로 로그인한 경우, 회원의 아이디, AccessToken 값, RefreshToken 값을 담은 DTO를 응답함
    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto requestDto){
        return memberService.login(requestDto.getCooksaveId(), requestDto.getPassword());
    }
}
