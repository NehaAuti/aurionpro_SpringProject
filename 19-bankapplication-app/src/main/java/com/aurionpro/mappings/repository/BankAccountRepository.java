package com.aurionpro.mappings.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aurionpro.mappings.entity.BankAccount;
import com.aurionpro.mappings.entity.Customer;
@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Integer>{

	   
	    List<BankAccount> findByCustomer(Customer customer);

	    Optional<BankAccount> findByAccountNumber(String accountNumber);
	
	    boolean existsByAccountNumber(String accountNumber);

	    
	

}