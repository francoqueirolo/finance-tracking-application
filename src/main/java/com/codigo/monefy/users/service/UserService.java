package com.codigo.monefy.users.service;

import com.codigo.monefy.exception.NotFoundException;
import com.codigo.monefy.users.dto.UserDTO;
import com.codigo.monefy.users.mapper.UserMapper;
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
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO findById(Integer id) {
        log.info("Finding user by ID: {}", id);
        return userRepository.findById(id)
                .map(userMapper::mapToDTO)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    public Page<User> findAll(int page, int size) {
        log.info("Finding all users - Page: {}, Size: {}", page, size);
        return userRepository.findAll(PageRequest.of(page, size));
    }
}
