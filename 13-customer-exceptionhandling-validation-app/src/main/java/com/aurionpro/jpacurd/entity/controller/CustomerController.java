package com.aurionpro.jpacurd.entity.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.jpacurd.dto.PageResponseCustomer;
import com.aurionpro.jpacurd.entitys.Customer;
import com.aurionpro.jpacurd.entity.services.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customerapp")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer")
    public ResponseEntity<PageResponseCustomer<Customer>> getAllCustomers(
        @RequestParam int pageno, 
        @RequestParam int pagesize) 
    {
        return ResponseEntity.ok(customerService.getAllCustomers(pageno, pagesize));
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable int id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping("/customer")
    public ResponseEntity<String> addCustomer(@Valid @RequestBody Customer customer) {
        customerService.addCustomer(customer);
        return ResponseEntity.ok("Customer added successfully");
    }

    @DeleteMapping("/customer/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable int id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok("Customer deleted successfully");
    }

    @GetMapping("/customers")
    public ResponseEntity<PageResponseCustomer<Customer>> getAllCustomersByName(
        @RequestParam(required = false) String firstName, 
        @RequestParam int pageno, 
        @RequestParam int pagesize) 
    {
        if (firstName != null) {
            return ResponseEntity.ok(customerService.getAllCustomersByFirstName(firstName, pageno, pagesize));
        } else {
            return ResponseEntity.ok(customerService.getAllCustomers(pageno, pagesize));
        }
    }
}