package com.aurionpro.mappings.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.aurionpro.mappings.dto.CustomerDto;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.exception.UserApiException;
import com.aurionpro.mappings.repository.CustomerRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService  {

	    @Autowired
	    private CustomerRepository customerRepository;
	
	    @Autowired
	    private PasswordEncoder passwordEncoder;

	    
	    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	    
	    @Override
	    public Customer addCustomer(Customer customer) {
	        logger.info("Adding new customer: {}", customer);
	        try {
	            Customer savedCustomer = customerRepository.save(customer);
	            logger.info("Customer added successfully with ID: {}", savedCustomer.getCustomerId());
	            return savedCustomer;
	        } catch (Exception e) {
	            logger.error("Error occurred while adding customer: {}", e.getMessage());
	            throw new UserApiException("Failed to add customer", e);
	        }
	    }

	    @Override
	    public Customer editCustomer(int customerId, String currentPassword, String newPassword, String newFirstName, String newLastName) {
	        logger.info("Editing customer with ID: {}", customerId);
	        try {
	            Customer existingCustomer = getCustomerById(customerId);

	            // Compare the plain text password with the stored hashed password
	            if (!passwordEncoder.matches(currentPassword, existingCustomer.getPassword())) {
	                logger.error("Current password is incorrect for customer ID: {}", customerId);
	                throw new UserApiException("Current password is incorrect", HttpStatus.UNAUTHORIZED);
	            }

	            // Update the password if a new one is provided
	            if (newPassword != null && !newPassword.isEmpty()) {
	                existingCustomer.setPassword(passwordEncoder.encode(newPassword));
	                logger.info("Password updated for customer ID: {}", customerId);
	            }

	            // Update the first name if provided
	            if (newFirstName != null && !newFirstName.isEmpty()) {
	                existingCustomer.setFirstName(newFirstName);
	                logger.info("First name updated for customer ID: {}", customerId);
	            }

	            // Update the last name if provided
	            if (newLastName != null && !newLastName.isEmpty()) {
	                existingCustomer.setLastName(newLastName);
	                logger.info("Last name updated for customer ID: {}", customerId);
	            }

	            // Save the updated customer
	            Customer updatedCustomer = customerRepository.save(existingCustomer);
	            logger.info("Customer updated successfully with ID: {}", customerId);
	            return updatedCustomer;
	        } catch (Exception e) {
	            logger.error("Error occurred while editing customer with ID {}: {}", customerId, e.getMessage());
	            throw new UserApiException("Failed to edit customer", e);
	        }
	    }

	    @Override
	    public Customer findByCustomerId(Integer customerId) {
	        logger.info("Finding customer by ID: {}", customerId);
	        try {
	            return customerRepository.findById(customerId).orElse(null);
	        } catch (Exception e) {
	            logger.error("Error occurred while finding customer by ID {}: {}", customerId, e.getMessage());
	            throw new UserApiException("Failed to find customer by ID", e);
	        }
	    }

	    @Override
	    public PageResponse<Customer> getAllCustomers(int pageNo, int pageSize) {
	        logger.info("Retrieving all customers with pagination - PageNo: {}, PageSize: {}", pageNo, pageSize);
	        try {
	            Pageable pageable = PageRequest.of(pageNo, pageSize);
	            Page<Customer> customersPage = customerRepository.findAll(pageable);

	            boolean isLastPage = customersPage.isLast();

	            PageResponse<Customer> pageResponse = new PageResponse<>(
	                customersPage.getContent(),
	                customersPage.getNumber(),
	                customersPage.getSize(),
	                customersPage.getTotalElements(),
	                customersPage.getTotalPages(),
	                isLastPage
	            );

	            logger.info("Retrieved {} customers on page {}", pageResponse.getContent().size(), pageNo);
	            return pageResponse;
	        } catch (Exception e) {
	            logger.error("Error occurred while retrieving customers: {}", e.getMessage());
	            throw new UserApiException("Failed to retrieve customers", e);
	        }
	    }

	    @Override
	    public Customer getCustomerById(int customerId) {
	        logger.info("Retrieving customer with ID: {}", customerId);
	        try {
	            return customerRepository.findById(customerId)
	                    .orElseThrow(() -> {
	                        logger.error("Customer with ID {} not found", customerId);
	                        return new UserApiException("Customer with ID " + customerId + " not found", HttpStatus.NOT_FOUND);
	                    });
	        } catch (UserApiException e) {
	            throw e; // Rethrow custom exceptions to avoid double logging
	        } catch (Exception e) {
	            logger.error("Error occurred while retrieving customer with ID {}: {}", customerId, e.getMessage());
	            throw new UserApiException("Failed to retrieve customer", e);
	        }
	    }
}
	   

//	    @Override
//	    public void deleteCustomer(int customerId) {
//	        logger.info("Deleting customer with ID: {}", customerId);
//	        if (customerRepository.existsById(customerId)) {
//	            customerRepository.deleteById(customerId);
//	            logger.info("Customer deleted successfully with ID: {}", customerId);
//	        } else {
//	            logger.error("Customer with ID {} not found", customerId);
//	            throw new RuntimeException("Customer with ID " + customerId + " not found");
//	        }
//	    }

	   



	    
	 