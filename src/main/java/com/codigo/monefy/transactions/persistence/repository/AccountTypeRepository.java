package com.codigo.monefy.transactions.persistence.repository;

import com.codigo.monefy.transactions.persistence.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
}
