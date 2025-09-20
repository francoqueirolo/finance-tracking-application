package com.codigo.monefy.transactions.controller;

import com.codigo.monefy.transactions.dto.AccountResponseDTO;
import com.codigo.monefy.transactions.dto.CreateAccountRequestDTO;
import com.codigo.monefy.transactions.service.AccountService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/accounts",  produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccountById(@PathVariable Integer id) {
        AccountResponseDTO account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    // Listar las cuentas por usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AccountResponseDTO>> getAccountByUserId(@PathVariable Integer userId) {
        // Lógica para obtener las cuentas por userId
        List<AccountResponseDTO> account = accountService.getAccountByUserId(userId);
        return ResponseEntity.ok().body(account);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<Void> createAccountForUser(
            @PathVariable Integer userId,
            @RequestBody CreateAccountRequestDTO accountRequest) {
        // Lógica para crear una cuenta para el usuario con userId
        accountService.createAccountForUser(userId, accountRequest);
        return ResponseEntity.ok().build();
    }

    // TODO: Tienen que implementar los metodos de actualizar, borrar y cambiar el estado de una cuenta.
}
