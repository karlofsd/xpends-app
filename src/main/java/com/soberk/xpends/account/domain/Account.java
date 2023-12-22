package com.soberk.xpends.account.domain;

import com.soberk.xpends.spot.domain.Spot;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Getter @Setter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name="id")
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "account")
    private List<Spot> spot;

    public Account(UUID id) {
        this.id = id;
    }

    public Account(String name, String description){
        this.name = name;
        this.description = description;
    }
}
