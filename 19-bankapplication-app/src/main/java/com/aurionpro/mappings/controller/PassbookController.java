package com.aurionpro.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aurionpro.mappings.entity.Passbook;
import com.aurionpro.mappings.service.PassbookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class PassbookController {

    @Autowired
    private PassbookService passbookService;

    @GetMapping("/passbooks/{senderAccountNumber}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getPassbooksBySenderAccountNumber(
            @Valid @PathVariable String senderAccountNumber) {
        List<Passbook> passbooks = passbookService.getPassbooksBySenderAccountNumber(senderAccountNumber);
        if (passbooks.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No passbook records found for this account number.");
        }
        return ResponseEntity.ok(passbooks);
    }
}
