package com.codigo.monefy.transactions.controller;

import com.codigo.monefy.transactions.dto.CreateTransactionRequestDTO;
import com.codigo.monefy.transactions.dto.TransactionDTO;
import com.codigo.monefy.transactions.service.TransactionService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/transactions",  produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionDTO> getTransactionById(
            @PathVariable Integer id
    ) {
        // Lógica para obtener una transacción por su ID
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<List<TransactionDTO>> getTransactionsByAccountId(
            @PathVariable Integer accountId
    ) {
        // Lógica para obtener las transacciones por accountId
        List<TransactionDTO> transactions = transactionService.getTransactionsByAccountId(accountId);
        return ResponseEntity.ok().body(transactions);
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(
            @RequestBody CreateTransactionRequestDTO createTransactionRequestDTO
    ) {
        // Lógica para crear una nueva transacción
        transactionService.createTransaction(createTransactionRequestDTO);
        return ResponseEntity.ok().build();
    }

}
