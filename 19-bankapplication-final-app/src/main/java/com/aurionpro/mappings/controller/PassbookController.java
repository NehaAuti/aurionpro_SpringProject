package com.aurionpro.mappings.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.aurionpro.mappings.entity.Passbook;
import com.aurionpro.mappings.entity.Transaction;
import com.aurionpro.mappings.service.EmailService;
import com.aurionpro.mappings.service.PassbookService;

import jakarta.mail.MessagingException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class PassbookController {

    @Autowired
    private PassbookService passbookService;
    
    @Autowired
    private EmailService emailService;
    

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
    // Endpoint to generate and send a passbook as a PDF via email
    @PostMapping("/passbooks/send")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<String> generateAndSendPassbook(
            @RequestParam("toEmail") String toEmail,
            @RequestParam("senderAccountNumber") String senderAccountNumber) {
        
        try {
            passbookService.generateAndSendPassbook(toEmail, senderAccountNumber);
            return ResponseEntity.ok("Passbook sent successfully to " + toEmail);
        } catch (MessagingException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to send passbook: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Invalid account number: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
}