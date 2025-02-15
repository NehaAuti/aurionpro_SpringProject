package com.aurionpro.mappings.service;

import java.util.Collections;
import java.util.Optional;

import com.aurionpro.mappings.dto.CustomerDto;
import com.aurionpro.mappings.entity.Admin;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.entity.UserRole;
import com.aurionpro.mappings.exception.AdminNotFoundException;
import com.aurionpro.mappings.exception.CustomerNotFoundException;
import com.aurionpro.mappings.exception.InvalidCredentialsException;
import com.aurionpro.mappings.exception.UserApiException;
import com.aurionpro.mappings.repository.AdminRepository;
import com.aurionpro.mappings.repository.CustomerRepository;
import com.aurionpro.mappings.repository.RoleRepository;
import com.aurionpro.mappings.repository.UserRepository;
import com.aurionpro.mappings.security.JwtTokenProvider;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class LoginServiceImpl implements LoginService{
	 	
//	@Autowired
//    private  AuthenticationManager authenticationManager;
//	@Autowired
//    private  UserRepository userRepo;
//	@Autowired
//    private  RoleRepository roleRepo;
//	@Autowired
//    private  PasswordEncoder passwordEncoder;
//	@Autowired
//    private  JwtTokenProvider tokenProvider;
	@Autowired
    private  CustomerRepository customerRepo;
//	@Autowired
//	private  UserDetailsService userDetailsService;
//
//	    @Autowired
//	    private AdminRepository adminRepository;
//	    
//	    private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
//
//	    @Override
//	    public String loginCustomer(String emailId, String password) {
//	        logger.info("Attempting to login customer with email: {}", emailId);
//
//	        Optional<Customer> customerOptional = customerRepo.findByEmailId(emailId);
//	        if (customerOptional.isPresent()) {
//	            Customer customer = customerOptional.get();
//	            if (customer.getPassword().equals(password)) {
//	                logger.info("Customer login successful for email: {}", emailId);
//	                return "Customer login successful!";
//	            } else {
//	                logger.error("Invalid password for email: {}", emailId);
//	                throw new InvalidCredentialsException("Invalid password.");
//	            }
//	        } else {
//	            logger.error("Invalid email ID: {}", emailId);
//	            throw new CustomerNotFoundException("Invalid email ID.");
//	        }
//	    }
//
//	    @Override
//	    public String loginAdmin(String username, String password) {
//	        logger.info("Attempting to login admin with username: {}", username);
//
//	        Optional<Admin> adminOptional = adminRepository.findByUsername(username);
//	        if (adminOptional.isPresent()) {
//	            Admin admin = adminOptional.get();
//	            if (admin.getPassword().equals(password)) {
//	                if (admin.getRole() == UserRole.ADMIN) {
//	                    logger.info("Admin login successful for username: {}", username);
//	                    return "Admin login successful!";
//	                } else {
//	                    logger.error("Access denied for username: {}. Not an admin.", username);
//	                    throw new InvalidCredentialsException("Access denied. Not an admin.");
//	                }
//	            } else {
//	                logger.error("Invalid password for username: {}", username);
//	                throw new InvalidCredentialsException("Invalid password.");
//	            }
//	        } else {
//	            logger.error("Invalid username: {}", username);
//	            throw new AdminNotFoundException("Invalid username.");
//	        }
//	    }
//	    
//	    @Override
//	    public String loginWithRole(String role, String usernameOrEmail, String password) {
//	        logger.info("Attempting login with role: {}, usernameOrEmail: {}", role, usernameOrEmail);
//
//	        if ("admin".equalsIgnoreCase(role)) {
//	            return loginAdmin(usernameOrEmail, password);
//	        } else if ("customer".equalsIgnoreCase(role)) {
//	            return loginCustomer(usernameOrEmail, password);
//	        } else {
//	            logger.error("Invalid role specified: {}", role);
//	            throw new InvalidCredentialsException("Invalid role.");
//	        }
//	    }
//	    
//	    @Override
//	    public String addAdmin(Admin admin) {
//	        logger.info("Attempting to add admin with username: {}", admin.getUsername());
//
//	        if (adminRepository.findByUsername(admin.getUsername()).isPresent()) {
//	            logger.error("Username already exists: {}", admin.getUsername());
//	            throw new InvalidCredentialsException("Username already exists.");
//	        }
//	        adminRepository.save(admin);
//	        logger.info("Admin added successfully with username: {}", admin.getUsername());
//	        return "Admin added successfully!";
//	    }

//	    @Override
//	    public String customerRegister(CustomerDto registration) {
//	        if (customerRepo.findByEmail(registration.getEmailId()).isPresent()) {
//	            throw new UserApiException(HttpStatus.BAD_REQUEST, "Customer with this email already exists.");
//	        }
//
//	        Customer customer = new Customer();
//	        customer.setFirstName(registration.getFirstName());
//	        customer.setLastName(registration.getLastName());
//	        customer.setEmailId(registration.getEmailId());
//	        customer.setPassword(passwordEncoder.encode(registration.getPassword()));
//	        customer.setDateOfBirth(registration.getDateOfBirth());
//
//	        // Set role for the customer (if needed)
//	        Role customerRole = roleRepo.findByRoleName("ROLE_CUSTOMER")
//	                .orElseThrow(() -> new UserApiException(HttpStatus.BAD_REQUEST, "Role not found."));
//	        customer.setRoles(Collections.singletonList(customerRole));
//
//	        customerRepo.save(customer);
//
//	        // Save to User table
//	        User user = new User();
//	        user.setUsername(customer.getEmailId());
//	        user.setPassword(passwordEncoder.encode(customer.getPassword()));
//	        user.setRoles(Collections.singletonList(customerRole));
//
//	        userRepo.save(user);
//
//	        return "Customer added successfully!";
//	    }

	}
	