package com.aurionpro.dbconnect.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dbconnect.entity.Payment;
import com.aurionpro.dbconnect.service.PaymentService;



@RestController
@RequestMapping("/paymentapp")
public class PaymentController {
	
	@Autowired
	private PaymentService paymentService;
	
	@GetMapping("/payment")
	public ResponseEntity<List<Payment>> getAllPayment()
	{
		return ResponseEntity.ok(paymentService.getAllPayment());
	}
	
	@GetMapping("/payment/{paymentId}")
	public ResponseEntity<Payment> getPayment(@PathVariable int paymentId)
	{
		return ResponseEntity.ok(paymentService.getPayment(paymentId));
	}
	
	
	@PostMapping("/payment")
	public String addPayment(@RequestBody Payment payment)
	{
		paymentService.addPayment(payment);
		return "Record added successfully"; 
	}
	
	@DeleteMapping("/payment/{paymentId}")
    public ResponseEntity<String> deletePayment(@PathVariable int paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.ok(" deleted successfully");
    }

}
