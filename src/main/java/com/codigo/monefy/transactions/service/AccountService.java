package com.codigo.monefy.transactions.service;

import com.codigo.monefy.exception.NotFoundException;
import com.codigo.monefy.transactions.dto.AccountResponseDTO;
import com.codigo.monefy.transactions.dto.CreateAccountRequestDTO;
import com.codigo.monefy.transactions.mapper.AccountMapper;
import com.codigo.monefy.transactions.persistence.entity.Account;
import com.codigo.monefy.transactions.persistence.entity.AccountType;
import com.codigo.monefy.transactions.persistence.repository.AccountRepository;
import com.codigo.monefy.transactions.persistence.repository.AccountTypeRepository;
import com.codigo.monefy.users.persistence.entity.User;
import com.codigo.monefy.users.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AccountService {
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccountTypeRepository accountTypeRepository;
    private final AccountMapper accountMapper;

    public AccountService(UserRepository userRepository, AccountRepository accountRepository, AccountTypeRepository accountTypeRepository, AccountMapper accountMapper) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.accountMapper = accountMapper;
    }

    @Transactional(readOnly = true)
    public AccountResponseDTO getAccountById(Integer id) {
        log.info("Fetching account with ID: {}", id);
        return accountRepository.findById(id)
                .map(accountMapper::mapToDto)
                .orElseThrow(() -> new NotFoundException("Account not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<AccountResponseDTO> getAccountByUserId(Integer userId) {
        log.info("Fetching account by user ID: {}", userId);
        List<Account> accounts = accountRepository.findAllByUserId(userId);
        if (accounts.isEmpty()) {
            throw new NotFoundException("No accounts found for user ID: " + userId);
        } else {
            return accounts.stream()// Agarrando individualmente cada cuenta y la convierto a dto cada uno
                    .map(accountMapper::mapToDto)
                    .toList();
        }
    }

    public void createAccountForUser(Integer userId, CreateAccountRequestDTO createAccountRequestDTO) {
        log.info("Creating account for user ID: {}", userId);
        Account account = new Account();

        Optional<User> user = userRepository.findById(userId);

        // Setear el usuario
        account.setUser(
                user.orElseThrow(() ->
                        new NotFoundException("User not found with ID: " + userId))
        );

        Optional<AccountType> accountType = accountTypeRepository.findById(createAccountRequestDTO.getAccountTypeId());

        // Setear el tipo de cuenta
        account.setAccountType(
                accountType.orElseThrow(() ->
                        new NotFoundException("Account type not found with ID: " + createAccountRequestDTO.getAccountTypeId()))
        );

        account.setName(createAccountRequestDTO.getName());
        account.setDescription(createAccountRequestDTO.getDescription());
        account.setInitialBalance(createAccountRequestDTO.getInitialBalance());
        account.setCurrentBalance(createAccountRequestDTO.getCurrentBalance());
        account.setActive(createAccountRequestDTO.isActive());
        account.setBankName(createAccountRequestDTO.getBankName());
        account.setAccountNumber(createAccountRequestDTO.getAccountNumber());

        accountRepository.save(account);
        log.info("Account created successfully for user ID: {}", userId);
    }
}
