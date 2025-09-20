package com.codigo.monefy.transactions.mapper;

import com.codigo.monefy.transactions.dto.AccountResponseDTO;
import com.codigo.monefy.transactions.persistence.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountResponseDTO mapToDto(Account account) {
        if (account == null) {
            return null;
        }

        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setName(account.getName());
        dto.setDescription(account.getDescription());
        dto.setInitialBalance(account.getInitialBalance());
        dto.setCurrentBalance(account.getCurrentBalance());
        dto.setActive(account.isActive());
        dto.setBankName(account.getBankName());
        dto.setAccountNumber(account.getAccountNumber());

        if (account.getUser() != null) {
            dto.setUserId(account.getUser().getId());
            dto.setEmail(account.getUser().getEmail());
            dto.setAccountTypeId(account.getAccountType().getId());
        }

        return dto;
    }
}
