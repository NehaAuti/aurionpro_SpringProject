package com.aurionpro.dbconnect.service;

import java.util.List;

import com.aurionpro.dbconnect.entity.Loan;

public interface LoanService {
	List<Loan> getAllLoan();
    Loan getLoan(int loanId);
    void addLoan(Loan loan);
    void deleteLoan(int loanId);
    

}
