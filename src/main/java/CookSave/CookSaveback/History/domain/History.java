package CookSave.CookSaveback.History.domain;

import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class History extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String name;  // 요리명

    @Column(nullable = false)
    private Integer total;  // 사용한 재료값 총합

    @Builder
    public History(Member member, String name, Integer total){
        this.member = member;
        this.name = name;
        this.total = total;
    }
}
