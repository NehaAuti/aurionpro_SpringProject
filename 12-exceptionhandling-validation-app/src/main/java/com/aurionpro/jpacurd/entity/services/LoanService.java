package com.aurionpro.jpacurd.entity.services;

import com.aurionpro.jpacurd.dto.PageResponseLoan;
import com.aurionpro.jpacurd.entity.Loan;
import com.aurionpro.jpacurd.entity.LoanStatus;
public interface LoanService {

    PageResponseLoan<Loan> getAllLoans(int pageno, int pagesize);

    void addLoan(Loan loan);

    void deleteLoan(int loanId);

	Loan getByLoanId(int loanId);

	PageResponseLoan<Loan> getAllLoansByLoanStatus(LoanStatus loanStatus, int pageno, int pagesize);
}