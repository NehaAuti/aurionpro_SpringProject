package com.aurionpro.jpacurd.entity.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.aurionpro.jpacurd.dto.PageResponseLoan;
import com.aurionpro.jpacurd.entity.Loan;
import com.aurionpro.jpacurd.entity.LoanStatus;
import com.aurionpro.jpacurd.exception.LoanNotFoundException;
import com.aurionpro.jpacurd.repository.LoanRepository;

@Service
public class LoanServiceImpl implements LoanService {

    @Autowired
    private LoanRepository loanRepo;
    
    @Override
    public PageResponseLoan<Loan> getAllLoans(int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        Page<Loan> loanPage = loanRepo.findAll(pageable);
        
        PageResponseLoan<Loan> loanPageResponse = new PageResponseLoan<>();
        loanPageResponse.setTotalPages(loanPage.getTotalPages());
        loanPageResponse.setSize(loanPage.getSize());
        loanPageResponse.setTotalElements(loanPage.getTotalElements());
        loanPageResponse.setLastPage(loanPage.isLast());
        loanPageResponse.setContent(loanPage.getContent());
        return loanPageResponse;
    }

    @Override
    public void addLoan(Loan loan) {
        loanRepo.save(loan);
    }

    @Override
    public Loan getByLoanId(int loanId) {
        Optional<Loan> dbLoan = loanRepo.findById(loanId);
        if (!dbLoan.isPresent()) {
            throw new LoanNotFoundException();
        }
        return dbLoan.get();
    }

    @Override
    public void deleteLoan(int loanId) {
    	Optional<Loan> dbLoan = loanRepo.findById(loanId);
        if (!dbLoan.isPresent()) {
            throw new LoanNotFoundException();
        }
        loanRepo.deleteById(loanId);
    }

    @Override
    public PageResponseLoan<Loan> getAllLoansByLoanStatus(LoanStatus loanStatus, int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        Page<Loan> loanPage = loanRepo.findByLoanStatus(loanStatus, pageable);
        
        PageResponseLoan<Loan> loanPageResponse = new PageResponseLoan<>();
        loanPageResponse.setTotalPages(loanPage.getTotalPages());
        loanPageResponse.setSize(loanPage.getSize());
        loanPageResponse.setTotalElements(loanPage.getTotalElements());
        loanPageResponse.setLastPage(loanPage.isLast());
        loanPageResponse.setContent(loanPage.getContent());
        return loanPageResponse;
    }
}