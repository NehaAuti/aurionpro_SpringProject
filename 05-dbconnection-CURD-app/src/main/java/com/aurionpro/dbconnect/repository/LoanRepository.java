package com.aurionpro.dbconnect.repository;

import java.util.List;

import com.aurionpro.dbconnect.entity.Loan;

public interface LoanRepository {
	List<Loan> getAllLoan();
    Loan getLoan(int loanId);
    void addLoan(Loan loan);
    void deleteLoan(int loanId);

}
