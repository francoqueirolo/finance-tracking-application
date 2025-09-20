package com.codigo.monefy.transactions.service;

import com.codigo.monefy.exception.NotFoundException;
import com.codigo.monefy.transactions.dto.CreateTransactionRequestDTO;
import com.codigo.monefy.transactions.dto.TransactionDTO;
import com.codigo.monefy.transactions.mapper.TransactionMapper;
import com.codigo.monefy.transactions.persistence.entity.Account;
import com.codigo.monefy.transactions.persistence.entity.Category;
import com.codigo.monefy.transactions.persistence.entity.Transaction;
import com.codigo.monefy.transactions.persistence.repository.AccountRepository;
import com.codigo.monefy.transactions.persistence.repository.CategoryRepository;
import com.codigo.monefy.transactions.persistence.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final CategoryRepository categoryRepository;
    private final TransactionMapper transactionMapper;

    public TransactionService(TransactionRepository transactionRepository, AccountRepository accountRepository, CategoryRepository categoryRepository, TransactionMapper transactionMapper) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
        this.categoryRepository = categoryRepository;
        this.transactionMapper = transactionMapper;
    }

    @Transactional(readOnly = true)
    public TransactionDTO getTransactionById(Integer id) {
        log.info("Fetching transaction with ID: {}", id);
        return transactionRepository.findById(id)
                .map(transactionMapper::mapToDto)
                .orElseThrow(
                        () -> new NotFoundException("Transaction not found with ID: " + id)
                );
    }

    @Transactional(readOnly = true)
    public List<TransactionDTO> getTransactionsByAccountId(Integer accountId) {
        log.info("Fetching transactions by account ID: {}", accountId);
        return transactionRepository.findAll().stream()
                .filter(transaction -> transaction.getAccount().getId().equals(accountId))
                .map(transactionMapper::mapToDto)
                .toList();
    }

    public void createTransaction(CreateTransactionRequestDTO createTransactionRequestDTO) {
        log.info("Creating transaction: {}", createTransactionRequestDTO);

        Transaction transaction = new Transaction();
        Optional<Account> account = accountRepository.findById(createTransactionRequestDTO.getAccountId());

        transaction.setAccount(
                account.orElseThrow(() ->
                        new NotFoundException("Account not found with ID: " + createTransactionRequestDTO.getAccountId()))
        );

        Optional<Category> category = categoryRepository.findById(createTransactionRequestDTO.getCategoryId());
        transaction.setCategory(
                category.orElseThrow(() ->
                        new NotFoundException("Category not found with ID: " + createTransactionRequestDTO.getCategoryId()))
        );
        transaction.setAmount(createTransactionRequestDTO.getAmount());
        transaction.setDescription(createTransactionRequestDTO.getDescription());
        transaction.setTransactionDate(createTransactionRequestDTO.getTransactionDate());
        transaction.setTransactionType(createTransactionRequestDTO.getTransactionType());
        transaction.setNotes(createTransactionRequestDTO.getNotes());
        transaction.setLocation(createTransactionRequestDTO.getLocation());

        // METODO ACTUALIZA EL CURRENTBALANCE DE LA CUENTA EN BASE A SI ES EXPENSE O INCOME
        if (transaction.getTransactionType().equalsIgnoreCase("expense")) {
            account.ifPresent(acc -> {
                acc.setCurrentBalance(acc.getCurrentBalance().subtract(transaction.getAmount()));
                accountRepository.save(acc);
            });
        } else if (transaction.getTransactionType().equalsIgnoreCase("income")) {
            account.ifPresent(acc -> {
                acc.setCurrentBalance(acc.getCurrentBalance().add(transaction.getAmount()));
                accountRepository.save(acc);
            });
        }

        transactionRepository.save(transaction);

        log.info("Transaction created with ID: {}", transaction.getId());
    }
}
