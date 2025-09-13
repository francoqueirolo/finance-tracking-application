package com.codigo.monefy.transactions.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "budget_categories")
@Getter
@Setter
public class BudgetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id", nullable = false)
    private Budget budget;

    private BigDecimal spentAmount;
    private BigDecimal allocatedAmount;

    public BudgetCategory() {}

    public BudgetCategory(Integer id, Category category, Budget budget, BigDecimal spentAmount, BigDecimal allocatedAmount) {
        this.id = id;
        this.category = category;
        this.budget = budget;
        this.spentAmount = spentAmount;
        this.allocatedAmount = allocatedAmount;
    }
}
