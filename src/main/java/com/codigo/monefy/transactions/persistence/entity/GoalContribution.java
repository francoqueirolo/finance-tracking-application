package com.codigo.monefy.transactions.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "goal_contributions")
@Getter
@Setter
public class GoalContribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", nullable = false)
    private FinancialGoal financialGoal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_id", nullable = false)
    private Transaction transaction;

    private Double amount;
    private LocalDate contributionDate;
    private String notes;

    public GoalContribution() {}

    public GoalContribution(Long id, FinancialGoal financialGoal, Transaction transaction, Double amount, LocalDate contributionDate, String notes) {
        this.id = id;
        this.financialGoal = financialGoal;
        this.transaction = transaction;
        this.amount = amount;
        this.contributionDate = contributionDate;
        this.notes = notes;
    }
}
