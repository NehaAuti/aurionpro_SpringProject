package com.aurionpro.jpacurd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jpacurd.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}