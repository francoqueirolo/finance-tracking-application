package com.codigo.monefy.transactions.persistence.repository;

import com.codigo.monefy.transactions.persistence.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
