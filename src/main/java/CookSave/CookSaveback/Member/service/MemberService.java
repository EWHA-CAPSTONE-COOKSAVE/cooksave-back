package CookSave.CookSaveback.Member.service;

import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder encoder;

    public String signUp(String cooksaveId, String password, String passwordCheck){
        if(existsByCooksaveId(cooksaveId)) throw new RuntimeException(cooksaveId + "은 이미 존재하는 아이디입니다.");
        // else if (!(Objects.equals(password, passwordCheck))) throw new RuntimeException("비밀번호가 일치하지 않습니다.");

        memberRepository.save(
                Member.builder()
                        .cooksaveId(cooksaveId)
                        .password(encoder.encode(password))
                        .build()
        );
        return "회원가입이 완료되었습니다.";
        }

    @Transactional(readOnly = true)
    public boolean existsByCooksaveId(String cooksaveId){
        return memberRepository.existsByCooksaveId(cooksaveId);
    }
}
