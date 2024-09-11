package com.aurionpro.jpacurd.entity.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aurionpro.jpacurd.entity.Account;
import com.aurionpro.jpacurd.entity.Transaction;
import com.aurionpro.jpacurd.entity.TransactionType;
import com.aurionpro.jpacurd.repository.AccountRepository;
import com.aurionpro.jpacurd.repository.TransactionRepository;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    @Override
    public void creditAccount(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));
        
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        
        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.CREDIT);
        transactionRepository.save(transaction);
    }

    @Transactional
    @Override
    public void debitAccount(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
            .orElseThrow(() -> new RuntimeException("Account not found"));

        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);

        Transaction transaction = new Transaction();
        transaction.setAccountId(accountId);
        transaction.setAmount(amount);
        transaction.setType(TransactionType.DEBIT);
        transactionRepository.save(transaction);
    }
}