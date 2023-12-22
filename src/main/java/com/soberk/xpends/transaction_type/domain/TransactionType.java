package com.soberk.xpends.transaction_type.domain;

import com.soberk.xpends.transaction.domain.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "transaction_types")
@Getter @Setter
@NoArgsConstructor
public class TransactionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    private boolean deleted;

    @OneToMany(mappedBy = "type")
    private List<Transaction> transactions;

    public TransactionType(Long id) {
        this.id = id;
    }

    public TransactionType(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
