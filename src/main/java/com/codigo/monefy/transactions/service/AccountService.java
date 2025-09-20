package com.codigo.monefy.transactions.service;

import com.codigo.monefy.exception.NotFoundException;
import com.codigo.monefy.transactions.dto.AccountResponseDTO;
import com.codigo.monefy.transactions.mapper.AccountMapper;
import com.codigo.monefy.transactions.persistence.repository.AccountRepository;
import com.codigo.monefy.transactions.persistence.repository.AccountTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional(readOnly = true)
    public AccountResponseDTO getAccountById(Integer id) {
        log.info("Fetching account with ID: {}", id);
        return accountRepository.findById(id)
                .map(accountMapper::mapToDto)
                .orElseThrow(() -> new NotFoundException("Account not found with ID: " + id));
    }
}
