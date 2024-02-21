package CookSave.CookSaveback.Member.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long memberId;

    @Column(nullable = false)
    private String cooksaveId;

    @Column(nullable = false)
    private String password;

    @Column
    private Integer budget;  // 사용자가 예산 설정하기 전까지 null 값

    @Builder
    public Member(String cooksaveId, String password){
        this.cooksaveId = cooksaveId;
        this.password = password;
    }

    public void updateBudget(Integer budget){
        this.budget = budget;
    }
}
