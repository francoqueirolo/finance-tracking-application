package com.codigo.monefy.transactions.persistence.repository;

import com.codigo.monefy.transactions.persistence.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findAllByUserId(Integer id);
}
