package com.aurionpro.mappings.repository;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.SalaryAccount;

@Repository
public interface SalaryAccountRepository extends JpaRepository<SalaryAccount, String> {

   
    List<SalaryAccount> findByAccountHolderName(String accountHolderName);

    SalaryAccount findByAccountNumber(String accountNumber);

}
