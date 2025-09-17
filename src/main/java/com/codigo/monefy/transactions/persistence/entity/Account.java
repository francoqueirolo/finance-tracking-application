package com.codigo.monefy.transactions.persistence.entity;

import com.codigo.monefy.users.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "accounts")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType accountType;
    private String name;
    private String description;
    private BigDecimal initialBalance;
    private BigDecimal currentBalance;
    private boolean isActive;
    private String bankName;
    private String accountNumber;

    public Account() {}

    public Account(Integer id, User user, AccountType accountType, String name, String description, BigDecimal initialBalance, BigDecimal currentBalance, boolean isActive, String bankName, String accountNumber) {
        this.id = id;
        this.user = user;
        this.accountType = accountType;
        this.name = name;
        this.description = description;
        this.initialBalance = initialBalance;
        this.currentBalance = currentBalance;
        this.isActive = isActive;
        this.bankName = bankName;
        this.accountNumber = accountNumber;
    }
}
