package com.aurionpro.mappings.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.BankDto;
import com.aurionpro.mappings.entity.Bank;
import com.aurionpro.mappings.entity.SalaryAccount;
import com.aurionpro.mappings.service.BankService;

@RestController
@RequestMapping("/bankapp")
public class BankController {

	@Autowired
    private BankService bankService;

    @PostMapping("/bank")
    public ResponseEntity<Bank> addBank(@RequestBody BankDto bankDto) {
        Bank bank = bankService.addBank(bankDto);
        return new ResponseEntity<>(bank, HttpStatus.CREATED);
    }

    @PostMapping("bank/accounts/{bankId}")
    public ResponseEntity<Bank> allocateSalaryAccountsToBank(
            @PathVariable int bankId, 
            @RequestBody List<SalaryAccount> salaryAccounts) {
        
        Bank updatedBank = bankService.allocateSalaryAccountsToBank(bankId, salaryAccounts);
        
        if (updatedBank == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity<>(updatedBank, HttpStatus.OK);
    }

    @GetMapping("/bank")
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        return new ResponseEntity<>(banks, HttpStatus.OK);
    }

    @GetMapping("bank/{bankId}")
    public ResponseEntity<Bank> getBankById(@PathVariable int bankId) {
        Bank bank = bankService.getBankById(bankId);
        if (bank == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(bank, HttpStatus.OK);
    }
}