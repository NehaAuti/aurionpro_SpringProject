package com.aurionpro.jwtsecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.jwtsecurity.entity.Customer;
import com.aurionpro.jwtsecurity.services.CustomerService;

@RestController
@RequestMapping("/app")
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/customers")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Customer>> getAllCustomers()
	{
		return ResponseEntity.ok(customerService.getAllCustomers());
	}
	
	@GetMapping("/customers/{customerId}")
	@PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
	public ResponseEntity<Customer> getCustomerById(@PathVariable int customerId)
	{
		return ResponseEntity.ok(customerService.getCustomerById(customerId));
	}
}
