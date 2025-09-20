package com.codigo.monefy.transactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreateTransactionRequestDTO {

    Integer accountId;
    Integer categoryId;
    BigDecimal amount;
    String description;
    LocalDateTime transactionDate; // ISO 8601 format
    String transactionType; // e.g., "income" or "expense"
    String notes;
    String location;
}
