package com.aurionpro.jpacurd.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.jpacurd.entity.service.AccountService;

@RestController
@RequestMapping("/accountapp")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{accountId}/credit")
    public ResponseEntity<String> creditAccount(@PathVariable Long accountId, @RequestParam double amount) {
        accountService.creditAccount(accountId, amount);
        return ResponseEntity.ok("Account credited successfully");
    }

    @PostMapping("/{accountId}/debit")
    public ResponseEntity<String> debitAccount(@PathVariable Long accountId, @RequestParam double amount) {
        accountService.debitAccount(accountId, amount);
        return ResponseEntity.ok("Account debited successfully");
    }
}