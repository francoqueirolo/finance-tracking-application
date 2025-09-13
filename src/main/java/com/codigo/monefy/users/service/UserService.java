package com.codigo.monefy.users.service;

import com.codigo.monefy.users.persistence.entity.User;
import com.codigo.monefy.users.persistence.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j // Logging
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        log.info("Finding user by ID: {}", id);
        return userRepository.findById(id).orElse(null);
    }

    public Page<User> findAll(int page, int size) {
        log.info("Finding all users - Page: {}, Size: {}", page, size);
        return userRepository.findAll(PageRequest.of(page, size));
    }
}
