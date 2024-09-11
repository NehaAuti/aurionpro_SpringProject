package com.aurionpro.mappings.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mappings.entity.SalaryAccount;

public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, Integer> {

	Optional<SalaryAccount> findByAccountNumber(int accountNumber);

}
