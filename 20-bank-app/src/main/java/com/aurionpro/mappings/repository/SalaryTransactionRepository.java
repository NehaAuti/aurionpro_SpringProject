package com.aurionpro.mappings.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.SalaryTransaction;


@Repository
public interface SalaryTransactionRepository extends JpaRepository<SalaryTransaction, Integer> {

    List<SalaryTransaction> findBySalaryAccount_AccountNumber(String accountNumber);


}
