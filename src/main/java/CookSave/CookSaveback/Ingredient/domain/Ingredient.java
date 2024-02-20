package CookSave.CookSaveback.Ingredient.domain;

import CookSave.CookSaveback.Icon.domain.Icon;
import CookSave.CookSaveback.Member.domain.Member;
import CookSave.CookSaveback.Tag.domain.Tag;
import CookSave.CookSaveback.global.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Ingredient extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long ingredientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")  // 태그 지정 방식 정한 후 nullable=false 설정 여부 결정
    private Tag tag;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id", nullable = false)
    private Icon icon;  // 재료 아이콘 아이디

    @Column(nullable = false)
    private String name;  // 재료명

    @Column(nullable = false)
    private Integer price;  // 재료 한 개의 가격

    @Column(nullable = false)
    private Float amount;  // 보유한 재료의 양

    @Builder
    public Ingredient(Member member, Tag tag, Icon icon, String name, Integer price, Float amount){
        this.member = member;
        this.tag = tag;
        this.icon = icon;
        this.name = name;
        this.price = price;
        this.amount = amount;
    }

    public void updateIngredient(Icon icon, Float amount){
        this.icon = icon;
        this.amount = amount;
    }

    public void updateSubtractedIngredient(Float amount){
        this.amount = amount;
    }
}
