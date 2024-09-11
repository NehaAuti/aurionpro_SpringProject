package com.aurionpro.mappings.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Integer> {

 
    Bank findByIfscNumber(String ifscNumber);

  
    List<Bank> findByBankName(String bankName);
}
