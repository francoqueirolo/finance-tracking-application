package com.codigo.monefy.transactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class TransactionDTO {

    Integer id;
    Integer accountId;
    Integer categoryId;
    BigDecimal amount;
    String description;
    String transactionDate;
    String transactionType; // "income" or "expense"
    String notes;
    String location;
}
