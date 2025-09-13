package com.codigo.monefy.users.persistence.repository;

import com.codigo.monefy.users.persistence.entity.Role;
import com.codigo.monefy.users.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
