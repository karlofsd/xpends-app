package com.soberk.xpends.spot.domain;

import com.soberk.xpends.account.domain.Account;
import com.soberk.xpends.currency.domain.Currency;
import com.soberk.xpends.transaction.domain.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "spot")
@NoArgsConstructor
@Getter @Setter
public class Spot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "amount")
    private double amount;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @OneToMany(mappedBy = "spot", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Spot(Long id){
        this.id = id;
    }

    public Spot(double amount, Long currencyId, UUID accountId){
        this.amount = amount;
        this.currency = new Currency(currencyId);
        this.account = new Account(accountId);
    }
}