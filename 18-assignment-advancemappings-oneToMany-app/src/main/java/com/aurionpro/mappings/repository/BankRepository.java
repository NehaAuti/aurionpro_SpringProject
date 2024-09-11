package com.aurionpro.mappings.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.aurionpro.mappings.entity.Bank;

public interface BankRepository extends JpaRepository<Bank, Integer> {

}
