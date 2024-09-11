package com.aurionpro.dbconnect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dbconnect.entity.Loan;
import com.aurionpro.dbconnect.repository.LoanRepository;

import jakarta.transaction.Transactional;
@Service
public class LoanServiceImpl implements LoanService {
	
	@Autowired
	private LoanRepository loanRepo;

	@Override
	public List<Loan> getAllLoan() {
		// TODO Auto-generated method stub
		return loanRepo.getAllLoan();
	}

	@Override
	public Loan getLoan(int loanId) {
		// TODO Auto-generated method stub
		return loanRepo.getLoan(loanId);
	}


	@Override
	public void addLoan(Loan loan) {
		// TODO Auto-generated method stub
		loanRepo.addLoan(loan);
		
	}

	@Override
	public void deleteLoan(int loanId) {
		// TODO Auto-generated method stub
		loanRepo.deleteLoan(loanId);
		
	}

}
