package com.codigo.monefy.transactions.persistence.entity;

import com.codigo.monefy.users.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "acctount_types")
@Getter
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType accountType;
    private String name;
    private String description;
    private Double initialBalance;
    private Double currentBalance;
    private boolean isActive;
    private String bankName;
    private String accountNumber;

    public Account() {}

    public Account(Long id, User user, AccountType accountType, String name, String description, Double initialBalance, Double currentBalance, boolean isActive, String bankName, String accountNumber) {
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
