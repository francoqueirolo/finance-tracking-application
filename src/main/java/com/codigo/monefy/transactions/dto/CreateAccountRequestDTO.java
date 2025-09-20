package com.codigo.monefy.transactions.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateAccountRequestDTO {
    Integer accountTypeId;
    String name;
    String description;
    BigDecimal initialBalance;
    BigDecimal currentBalance;
    boolean isActive;
    String bankName;
    String accountNumber;
}
