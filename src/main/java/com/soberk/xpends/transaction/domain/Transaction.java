package com.soberk.xpends.transaction.domain;

import com.soberk.xpends.category.domain.Category;
import com.soberk.xpends.spot.domain.Spot;
import com.soberk.xpends.transaction_type.domain.TransactionType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Getter @Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "amount")
    private double amount;

    @Column(name = "description",length = 50)
    private String description;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "transaction_type_id", referencedColumnName = "id")
    private TransactionType type;

    @ManyToOne
    @JoinColumn(name = "spot_id", referencedColumnName = "id")
    private Spot spot;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Transaction(double amount, String description, LocalDateTime dateTime, Long typeId, Long spotId, Long categoryId) {
        this.amount = amount;
        this.description = description;
        this.dateTime = dateTime;
        this.type = new TransactionType(typeId);
        this.spot = new Spot(spotId);
        this.category = new Category(categoryId);
    }
}
