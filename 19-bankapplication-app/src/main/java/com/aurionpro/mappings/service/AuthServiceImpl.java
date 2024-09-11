package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.aurionpro.mappings.dto.CustomerDto;
import com.aurionpro.mappings.dto.LoginDTO;
import com.aurionpro.mappings.dto.RegistrationDTO;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.exception.UserApiException;
import com.aurionpro.mappings.repository.RoleRepository;
import com.aurionpro.mappings.repository.UserRepository;
import com.aurionpro.mappings.security.JwtTokenProvider;
import com.aurionpro.mappings.repository.CustomerRepository;

import lombok.AllArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);


	@Autowired
    private final AuthenticationManager authenticationManager;
	@Autowired
    private final UserRepository userRepo;
	@Autowired
    private final RoleRepository roleRepo;
	@Autowired
    private final PasswordEncoder passwordEncoder;
	@Autowired
    private final JwtTokenProvider tokenProvider;
	@Autowired
    private final CustomerRepository customerRepo;
	@Autowired
	private final UserDetailsService userDetailsService;

	 @Override
	    public User register(RegistrationDTO registration) {
	        logger.info("Registering new user with username: {}", registration.getUsername());

	        if (userRepo.existsByUsername(registration.getUsername())) {
	            logger.error("User with username {} already exists", registration.getUsername());
	            throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists");
	        }

	        User user = new User();
	        user.setUsername(registration.getUsername());
	        user.setPassword(passwordEncoder.encode(registration.getPassword()));

	        try {
	            List<Role> roles = new ArrayList<>();
	            Role userRole = roleRepo.findByRolename(registration.getRole())
	                .orElseThrow(() -> {
	                    logger.error("Role {} not found", registration.getRole());
	                    return new UserApiException(HttpStatus.NOT_FOUND, "Role not found");
	                });
	            roles.add(userRole);
	            user.setRoles(roles);

	            logger.info("User registered successfully with username: {}", registration.getUsername());
	            return userRepo.save(user);
	        } catch (Exception e) {
	            logger.error("Error occurred while registering user: {}", e.getMessage());
	            throw new UserApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register user");
	        }
	    }
	    
	 @Override
	 public Customer register(CustomerDto registration) {
	     logger.info("Registering new customer with email: {}", registration.getEmailId());

	     Optional<Customer> existingCustomerOpt = customerRepo.findByEmailId(registration.getEmailId());
	     if (existingCustomerOpt.isPresent()) {
	         logger.error("Customer with email {} already exists", registration.getEmailId());
	         throw new UserApiException(HttpStatus.BAD_REQUEST, "Customer already exists");
	     }

	     Customer customer = new Customer();
	     customer.setFirstName(registration.getFirstName());
	     customer.setLastName(registration.getLastName());
	     customer.setEmailId(registration.getEmailId());
	     customer.setPassword(passwordEncoder.encode(registration.getPassword()));
	     customer.setDateOfBirth(registration.getDateOfBirth());

	     Role customerRole;
	     try {
	         customerRole = roleRepo.findByRolename("ROLE_CUSTOMER")
	             .orElseThrow(() -> new UserApiException(HttpStatus.NOT_FOUND, "Role not found"));
	     } catch (UserApiException e) {
	         logger.error("Role not found during customer registration");
	         throw e;  // Re-throwing the exception to be handled by the controller
	     }

	     customer.setRoles(Collections.singletonList(customerRole));

	     try {
	         Customer savedCustomer = customerRepo.save(customer);
	         logger.info("Customer registered successfully with email: {}", registration.getEmailId());
	         return savedCustomer;
	     } catch (Exception e) {
	         logger.error("Error occurred while saving customer: {}", e.getMessage());
	         throw new UserApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to register customer");
	     }
	 }


	    @Override
	    public String login(LoginDTO loginDto) {
	        logger.info("Attempting to log in user with username: {}", loginDto.getUsername());

	        try {
	            UserDetails userDetails = userDetailsService.loadUserByUsername(loginDto.getUsername());
	            Authentication authentication = authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(
	                    userDetails.getUsername(),
	                    loginDto.getPassword(),
	                    userDetails.getAuthorities())
	            );

	            SecurityContextHolder.getContext().setAuthentication(authentication);

	            String token = tokenProvider.generateToken(authentication);
	            logger.info("Login successful for user: {}", loginDto.getUsername());
	            return token;
	        } catch (BadCredentialsException e) {
	            logger.error("Invalid username or password for user: {}", loginDto.getUsername());
	            throw new UserApiException(HttpStatus.UNAUTHORIZED, "Invalid username or password");
	        } catch (Exception e) {
	            logger.error("Error occurred during login: {}", e.getMessage());
	            throw new UserApiException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to log in user");
	        }
	    }
	}