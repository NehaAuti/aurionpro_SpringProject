package com.aurionpro.jpacurd.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.aurionpro.jpacurd.entity.Loan;
import com.aurionpro.jpacurd.entity.LoanStatus;

public interface LoanRepository extends JpaRepository<Loan, Integer> {

	Page<Loan> findByLoanStatus(LoanStatus loanStatus, Pageable pageable);

}
