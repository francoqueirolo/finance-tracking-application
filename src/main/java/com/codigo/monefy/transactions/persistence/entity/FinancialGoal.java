package com.codigo.monefy.transactions.persistence.entity;

import com.codigo.monefy.users.persistence.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "financial_goals")
@Getter
@Setter
public class FinancialGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;
    private String description;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private LocalDate targetDate;
    private String goalType;
    private boolean isAchieved;
    private boolean isActive;

    public FinancialGoal() {}

    public FinancialGoal(Integer id, User user, String name, String description, BigDecimal targetAmount, BigDecimal currentAmount, LocalDate targetDate, String goalType, boolean isAchieved, boolean isActive) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.description = description;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.targetDate = targetDate;
        this.goalType = goalType;
        this.isAchieved = isAchieved;
        this.isActive = isActive;
    }
}
