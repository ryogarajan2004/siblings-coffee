package com.siblingscup.coffee.repository;

import com.siblingscup.coffee.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
