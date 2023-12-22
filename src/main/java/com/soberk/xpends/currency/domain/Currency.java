package com.soberk.xpends.currency.domain;

import com.soberk.xpends.spot.domain.Spot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "currencies")
@Getter @Setter
@NoArgsConstructor
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String code;

    @Column
    private String symbol;

    @Column
    private double exchange;

    @Column
    private boolean deleted = false;

    @OneToMany(mappedBy = "currency")
    private List<Spot> spots;

    public Currency(Long id) {
        this.id = id;
    }

    public Currency(String name, String code, String symbol, double exchange){
        this.name= name;
        this.code=code;
        this.symbol = symbol;
        this.exchange = exchange;
    }
}
