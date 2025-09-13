package com.codigo.monefy.users.persistence.repository;

import com.codigo.monefy.users.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT * FROM users WHERE email = ? LIMIT 1
    Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);
}
