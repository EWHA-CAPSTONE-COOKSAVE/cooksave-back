package CookSave.CookSaveback.HistoryIngredient.domain;

import CookSave.CookSaveback.History.domain.History;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HistoryIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long historyIngId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "history_id", nullable = false)
    private History history;

    @Column(nullable = false)
    private String name;  // 재료명

    @Column(nullable = false)
    private Float amount;

    @Column(nullable = false)
    private Integer price;

    @Builder
    public HistoryIngredient(History history, String name, Float amount, Integer price){
        this.history = history;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }
}
