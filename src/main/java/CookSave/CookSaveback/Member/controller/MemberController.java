package CookSave.CookSaveback.Member.controller;

import CookSave.CookSaveback.Member.dto.LoginRequestDto;
import CookSave.CookSaveback.Member.dto.LoginResponseDto;
import CookSave.CookSaveback.Member.dto.RefreshRequestDto;
import CookSave.CookSaveback.Member.dto.SignUpRequestDto;
import CookSave.CookSaveback.Member.service.MemberService;
import CookSave.CookSaveback.Member.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;
    private final RefreshTokenService refreshTokenService;

    // 회원가입
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

    // RefreshToken을 이용해 새 AccessToken 발급 요청
    @PostMapping("/refresh")
    public LoginResponseDto refresh(@RequestBody RefreshRequestDto refreshRequestDto){
        return memberService.refresh(refreshRequestDto.getRefreshToken());
    }

    // 로그아웃
    // 전달받은 RefreshToken을 DB에서 삭제
    @DeleteMapping("/logout")
    public String logout(@RequestBody RefreshRequestDto refreshRequestDto) {
        refreshTokenService.deleteRefreshToken(refreshRequestDto.getRefreshToken());
        return "로그아웃되었습니다.";
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteMember(Authentication authentication){
        return ResponseEntity.ok().body(memberService.delete(authentication));
    }
}
