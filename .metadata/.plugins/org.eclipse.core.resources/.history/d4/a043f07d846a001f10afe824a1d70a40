package com.aurionpro.mappings.controller;

import com.aurionpro.mappings.dto.CustomerEditRequestDto;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.exception.CustomerNotFoundException;
import com.aurionpro.mappings.exception.InvalidInputException;
import com.aurionpro.mappings.service.CustomerService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankapp")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping("/editCustomer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<Customer> editCustomer(@Valid @RequestBody CustomerEditRequestDto customerEditRequestDto) {
        try {
            logger.info("Editing customer with ID: {}", customerEditRequestDto.getCustomerId());
            Customer updatedCustomer = customerService.editCustomer(
                    customerEditRequestDto.getCustomerId(),
                    customerEditRequestDto.getCurrentPassword(),
                    customerEditRequestDto.getNewPassword(),
                    customerEditRequestDto.getNewFirstName(),
                    customerEditRequestDto.getNewLastName()
            );
            logger.info("Customer updated successfully: {}", updatedCustomer);
            return ResponseEntity.ok(updatedCustomer);
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found: {}", e.getMessage());
            throw e;
        } catch (InvalidInputException e) {
            logger.error("Invalid input: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Unexpected error occurred while updating customer: {}", e.getMessage(), e);
            throw new InvalidInputException("Unexpected error occurred while updating customer");
        }
    }

    @GetMapping("/customers")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PageResponse<Customer>> getAllCustomers(@Valid @RequestParam int pageNo,
                                                                  @RequestParam int pageSize) {
        logger.info("Fetching all customers with pagination: pageNo = {}, pageSize = {}", pageNo, pageSize);
        try {
            PageResponse<Customer> pageResponse = customerService.getAllCustomers(pageNo, pageSize);
            logger.info("Customers fetched successfully, total records: {}", pageResponse.getTotalPages());
            return new ResponseEntity<>(pageResponse, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error occurred while fetching customers: {}", e.getMessage(), e);
            throw new InvalidInputException("Error occurred while fetching customers");
        }
    }

    @GetMapping("/customers/{customerId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Customer> getCustomerById(@Valid @PathVariable int customerId) {
        logger.info("Fetching customer with ID: {}", customerId);
        try {
            Customer customer = customerService.getCustomerById(customerId);
            logger.info("Customer fetched successfully: {}", customer);
            return ResponseEntity.ok(customer);
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred while retrieving customer: {}", e.getMessage(), e);
            throw new InvalidInputException("Error occurred while retrieving customer");
        }
    }
}

    // Endpoint to update an existing customer
//    @PostMapping("customer/{customerId}")
//    public Customer updateCustomer(@PathVariable int customerId, @Valid @RequestBody Customer customer) {
//        return customerService.updateCustomer(customerId, customer);
//    }
//
//    @PostMapping("customer/update")
//    public ResponseEntity<Customer> updatePartialCustomer(@RequestParam int customerId, @RequestBody Map<String, Object> updates) {
//        Customer updatedCustomer = customerService.updatePartial(customerId, updates);
//        return ResponseEntity.ok(updatedCustomer);
//    }


//    @DeleteMapping("/customer/{customerId}")
//    public ResponseEntity<Void> deleteCustomer(@RequestParam int customerId) {
//        try {
//            customerService.deleteCustomer(customerId);
//            return ResponseEntity.noContent().build();
//        } catch (CustomerNotFoundException e) {
//            throw e;
//        } catch (Exception e) {
//            throw new InvalidInputException("Error occurred while deleting customer");
//        }
//    }


// Endpoint to create a new customer
//@PostMapping("/customer")
//@PreAuthorize("hasRole('ADMIN')")
//public ResponseEntity<Customer> addCustomer(@RequestBody @Valid Customer customer) {
//  try {
//      Customer addedCustomer = customerService.addCustomer(customer);
//      return ResponseEntity.status(HttpStatus.CREATED).body(addedCustomer);
//  } catch (Exception e) {
//      throw new InvalidInputException("Invalid customer data provided");
//  }
//}



