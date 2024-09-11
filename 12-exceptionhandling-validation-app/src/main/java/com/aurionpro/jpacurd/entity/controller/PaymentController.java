package com.aurionpro.jpacurd.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.jpacurd.dto.PageResponsePayment;
import com.aurionpro.jpacurd.entity.Payment;
import com.aurionpro.jpacurd.entity.PaymentMode; // Ensure this matches your entity
import com.aurionpro.jpacurd.entity.services.PaymentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/paymentapp")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/payment")
    public ResponseEntity<PageResponsePayment<Payment>> getAllPayments(
        @RequestParam int pageno, 
        @RequestParam int pagesize) {
        return ResponseEntity.ok(paymentService.getAllPayments(pageno, pagesize));
    }


    @GetMapping("/payment/{id}")
    public ResponseEntity<Payment> getPaymentById(@PathVariable int id) {
        return ResponseEntity.ok(paymentService.getByPaymentId(id));
    }

    @PostMapping("/payment")
    public ResponseEntity<String> addPayment(@Valid @RequestBody Payment payment) {
        paymentService.addPayment(payment);
        return ResponseEntity.ok("Payment added successfully");
    }

    @DeleteMapping("/payment/{id}")
    public ResponseEntity<String> deletePayment(@PathVariable int id) {
        paymentService.deletePayment(id);
        return ResponseEntity.ok("Payment deleted successfully");
    }

    @GetMapping("/payments")
    public ResponseEntity<PageResponsePayment<Payment>> getAllPaymentsByMode(
        @RequestParam PaymentMode paymentMode, 
        @RequestParam int pageno, 
        @RequestParam int pagesize) 
    {
        return ResponseEntity.ok(paymentService.getAllPaymentsByPaymentMode(paymentMode, pageno, pagesize));
    }
}