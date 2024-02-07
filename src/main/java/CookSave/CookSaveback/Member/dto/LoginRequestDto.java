package CookSave.CookSaveback.Member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
    private String cooksaveId;
    private String password;
}
