package com.codigo.monefy.transactions.persistence.entity;

import com.codigo.monefy.users.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "budgets")
@Getter
@Setter
public class Budget {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;
    private String description;
    private BigDecimal totalAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private String budgetType;
    private boolean isActive;

    public Budget() {}

    public Budget(Integer id, User user, String name, String description, BigDecimal totalAmount, LocalDate startDate, LocalDate endDate, String budgetType, boolean isActive) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.totalAmount = totalAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetType = budgetType;
        this.isActive = isActive;
    }
}
