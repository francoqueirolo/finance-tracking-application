package com.codigo.monefy.transactions.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    private BigDecimal amount;
    private String description;
    private LocalDateTime transactionDate;
    private String transactionType; // e.g., "income" or "expense"
    private String notes;
    private String location;

    public Transaction() {}

    public Transaction(Integer id, Account account, Category category, BigDecimal amount, String description, LocalDateTime transactionDate, String transactionType, String notes, String location) {
        this.id = id;
        this.account = account;
        this.category = category;
        this.amount = amount;
        this.description = description;
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.notes = notes;
        this.location = location;
    }
}
