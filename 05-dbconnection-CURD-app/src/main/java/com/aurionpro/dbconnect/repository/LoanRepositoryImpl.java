package com.aurionpro.dbconnect.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aurionpro.dbconnect.entity.Loan;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
@Repository
public class LoanRepositoryImpl implements LoanRepository{
	
	@Autowired
	private EntityManager manager;

	@Override
	public List<Loan> getAllLoan() {
		// TODO Auto-generated method stub
		TypedQuery<Loan> query = manager.createQuery("select l from Loan l",Loan.class);
		return query.getResultList();
	}

	@Override
	public Loan getLoan(int loanId) {
		// TODO Auto-generated method stub
		return manager.find(Loan.class, loanId);
	}

	@Transactional
	@Override
	public void addLoan(Loan loan) {
		// TODO Auto-generated method stub
		manager.merge(loan);
		
	}

	@Transactional
	@Override
	public void deleteLoan(int loanId) {
		// TODO Auto-generated method stub
		Loan loan = manager.find(Loan.class, loanId);
        if (loan != null) {
            manager.remove(loan);
        }
    
	}

}
