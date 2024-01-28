package CookSave.CookSaveback.Member.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequestDto {
    private String cooksaveId;
    private String password;
    private String passwordCheck;  // 비밀번호 일치 확인을 위한 입력값
}
