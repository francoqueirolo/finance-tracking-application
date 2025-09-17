package com.codigo.monefy.transactions.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account_types")
@Getter
@Setter
public class AccountType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;

    public AccountType() {}

    public AccountType(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
