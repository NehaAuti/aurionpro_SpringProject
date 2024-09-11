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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dbconnect.entity.Customer;
import com.aurionpro.dbconnect.service.CustomerService;


@RestController
@RequestMapping("/customerapp")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	

	@GetMapping("/customer")
	public ResponseEntity<List<Customer>> getAllCustomer()
	{
		return ResponseEntity.ok(customerService.getAllCustomer());
	}
	
	@GetMapping("/customer/{customerId}")
	public ResponseEntity<Customer> getCustomer(@PathVariable int customerId)
	{
		return ResponseEntity.ok(customerService.getCustomer(customerId));
	}
	
	
	@PostMapping("/customer")
	public String addCustomer(@RequestBody Customer customer)
	{
		customerService.addCustomer(customer);
		return "Record added successfully"; 
	}
	
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getCustomerByName(@RequestParam String firstName, @RequestParam String lastName)
	{
		return ResponseEntity.ok(customerService.getCustomerByName(firstName, lastName));
	}
	 
    @DeleteMapping("/customer/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int customerId) {
        customerService.deleteCustomer(customerId);
        return ResponseEntity.ok("Customer deleted successfully");
    }
	

}
