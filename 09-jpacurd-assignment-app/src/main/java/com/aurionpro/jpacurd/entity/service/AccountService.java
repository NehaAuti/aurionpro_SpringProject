package com.aurionpro.jpacurd.entity.service;

public interface AccountService {
	void creditAccount(Long accountId, double amount);
    void debitAccount(Long accountId, double amount);

}
