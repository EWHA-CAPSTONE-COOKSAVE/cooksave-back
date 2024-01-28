package CookSave.CookSaveback.Member.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {
    private Long memberId;
    private String cooksaveId;
    private String accessToken;
    private String refreshToken;

    @Builder
    public LoginResponseDto(Long memberId, String cooksaveId, String accessToken, String refreshToken){
        this.memberId = memberId;
        this.cooksaveId = cooksaveId;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
