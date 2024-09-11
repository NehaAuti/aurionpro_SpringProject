package com.aurionpro.jpacurd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jpacurd.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
