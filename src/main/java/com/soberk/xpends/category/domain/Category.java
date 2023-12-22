package com.soberk.xpends.category.domain;

import com.soberk.xpends.transaction.domain.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter @Setter
@NoArgsConstructor
public class Category  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "deleted")
    private boolean deleted;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    public Category(Long id) {
        this.id = id;
    }

    public Category(String name, String description){
        this.name=name;
        this.description=description;
    }
}
