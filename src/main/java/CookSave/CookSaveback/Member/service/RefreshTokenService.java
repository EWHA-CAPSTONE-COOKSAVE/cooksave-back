package CookSave.CookSaveback.Member.service;

import CookSave.CookSaveback.Member.domain.RefreshToken;
import CookSave.CookSaveback.Member.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public void addRefreshToken(RefreshToken refreshToken){
        refreshTokenRepository.save(refreshToken);
    }
}
