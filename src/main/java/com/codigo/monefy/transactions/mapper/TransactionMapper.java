package com.codigo.monefy.transactions.mapper;

import com.codigo.monefy.transactions.dto.TransactionDTO;
import com.codigo.monefy.transactions.persistence.entity.Transaction;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class TransactionMapper {

    public TransactionDTO mapToDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setAccountId(transaction.getAccount().getId());
        dto.setCategoryId(transaction.getCategory().getId());
        dto.setAmount(transaction.getAmount());
        dto.setDescription(transaction.getDescription());
        String formatDate = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
                .format(transaction.getTransactionDate());
        dto.setTransactionDate(formatDate);
        dto.setTransactionType(transaction.getTransactionType());
        dto.setNotes(transaction.getNotes());
        dto.setLocation(transaction.getLocation());

        return dto;
    }
}
