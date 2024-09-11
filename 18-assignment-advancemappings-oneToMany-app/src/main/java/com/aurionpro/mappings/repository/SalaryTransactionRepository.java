package com.aurionpro.mappings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.mappings.entity.SalaryTransaction;


public interface SalaryTransactionRepository extends JpaRepository<SalaryTransaction, Integer>{

	List<SalaryTransaction> findBySalary_SalaryId(int salaryId);

}
