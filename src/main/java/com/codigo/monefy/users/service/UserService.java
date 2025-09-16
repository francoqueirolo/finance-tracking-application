package com.codigo.monefy.users.service;

import com.codigo.monefy.exception.NotFoundException;
import com.codigo.monefy.users.dto.RegistrationRequest;
import com.codigo.monefy.users.dto.UserDTO;
import com.codigo.monefy.users.dto.UserRegistrationResponse;
import com.codigo.monefy.users.mapper.UserMapper;
import com.codigo.monefy.users.persistence.entity.Role;
import com.codigo.monefy.users.persistence.entity.User;
import com.codigo.monefy.users.persistence.repository.RoleRepository;
import com.codigo.monefy.users.persistence.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
@Slf4j // Logging
@Transactional(rollbackFor = Exception.class)
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Integer id) {
        log.info("Finding user by ID: {}", id);
        return userRepository.findById(id)
                .map(userMapper::mapToDTO)// La sesion se pierde
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + id));
    }

    public Page<User> findAll(int page, int size) {
        log.info("Finding all users - Page: {}, Size: {}", page, size);
        return userRepository.findAll(PageRequest.of(page, size));
    }

    public void register(@Valid RegistrationRequest registrationRequest) {
        log.info("Registering user with email: {}", registrationRequest.getEmail());

        if (userRepository.existsByEmail(registrationRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use: " + registrationRequest.getEmail());
        }

        final User user = new User();
        user.setEmail(registrationRequest.getEmail());
        String username = registrationRequest.getFirstName().toLowerCase() + "." +
                registrationRequest.getLastName().toLowerCase(); // Andre Gallegos andre.gallegos
        user.setUsername(username);
        user.setPasswordHash(registrationRequest.getPassword());
        user.setFirstName(registrationRequest.getFirstName());
        user.setLastName(registrationRequest.getLastName());
        user.setPhone(registrationRequest.getPhone());
        user.setProfileImageUrl(registrationRequest.getProfileImageUrl());
        user.setActive(true);
        user.setVerified(false);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        // Asignar role cliente por defecto
        Role defaultRole = roleRepository.findById(3)
                .orElseThrow(() -> new NotFoundException("Role not found with ID: " + 3));

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);
        user.setRoles(roles);

        // Guardar usuario
        userRepository.save(user);
    }
}
