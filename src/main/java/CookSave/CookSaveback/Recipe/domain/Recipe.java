package CookSave.CookSaveback.Recipe.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long recipeId;

    @Column(nullable = false)
    private String name;  // 요리 이름

    @Column(nullable = false)
    private String mainIng;  // 주요 재료들

    @Column(nullable = false)
    private String content;  // 레시피 내용

    @Column
    private String image;  // 음식 사진 URL

    @Column
    private String video;  // 요리 영상 URL
}
