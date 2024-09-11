package com.aurionpro.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.CustomerDto;
import com.aurionpro.mappings.dto.JwtAuthResponse;
import com.aurionpro.mappings.dto.LoginDTO;
import com.aurionpro.mappings.dto.RegistrationDTO;
import com.aurionpro.mappings.entity.Admin;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.exception.CustomerNotFoundException;
import com.aurionpro.mappings.exception.InvalidInputException;
import com.aurionpro.mappings.exception.UserApiException;
import com.aurionpro.mappings.service.AuthService;
import com.aurionpro.mappings.service.LoginService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bankapp")
@Slf4j
public class AuthController {
	
	@Autowired
    private LoginService loginService;
	
	
	
	@Autowired
    private AuthService authService;

	@PostMapping("register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody RegistrationDTO registerDto) {
        try {
            log.info("Registering new admin: {}", registerDto.getUsername());
            User user = authService.register(registerDto);
            log.info("Admin registration successful for: {}", registerDto.getUsername());
            return ResponseEntity.ok(user);
        } catch (InvalidInputException e) {
            log.error("Invalid input during admin registration: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error during admin registration", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

	@PostMapping("register/customer")
	public ResponseEntity<Object> registerCustomer(@Valid @RequestBody CustomerDto registrationDto) {
	    try {
	        log.info("Registering new customer: {}", registrationDto.getEmailId());
	        Customer customer = authService.register(registrationDto);
	        log.info("Customer registration successful for: {}", registrationDto.getEmailId());
	        return ResponseEntity.ok(customer);
	    } catch (UserApiException e) {
	        log.error("Error during customer registration: {}", e.getMessage());
	        return ResponseEntity.status(e.getStatus()).body(e.getMessage());
	    } catch (Exception e) {
	        log.error("Unexpected error during customer registration", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred. Please try again later.");
	    }
	}

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDto) {
        try {
            log.info("User login attempt: {}", loginDto.getUsername());
            String token = authService.login(loginDto);
            log.info("User login successful: {}", loginDto.getUsername());
            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setAccessToken(token);
            return ResponseEntity.ok(jwtAuthResponse);
        } catch (CustomerNotFoundException e) {
            log.error("Customer not found: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (InvalidInputException e) {
            log.error("Invalid login input: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Unexpected error during login", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
	    
}

//    @PostMapping("/login/customer")
//    public ResponseEntity<String> loginCustomer(
//            @RequestParam String emailId,
//            @RequestParam String password) {
//        try {
//            String status = loginService.loginCustomer(emailId, password);
//            return ResponseEntity.ok(status);
//        } catch (CustomerNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (InvalidInputException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//
//    @PostMapping("/login/admin")
//    public ResponseEntity<String> loginAdmin(
//            @RequestParam String username,
//            @RequestParam String password) {
//        try {
//            String status = loginService.loginAdmin(username, password);
//            return ResponseEntity.ok(status);
//        } catch (CustomerNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (InvalidInputException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    
//    @PostMapping("/login")
//    public ResponseEntity<String> loginWithRole(
//            @RequestParam String role,
//            @RequestParam String usernameOrEmail,
//            @RequestParam String password) {
//        try {
//            String status = loginService.loginWithRole(role, usernameOrEmail, password);
//            return ResponseEntity.ok(status);
//        } catch (CustomerNotFoundException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
//        } catch (InvalidInputException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @PostMapping("/addAdmin")
//    public ResponseEntity<String> addAdmin(@RequestBody Admin admin) {
//        try {
//            String status = loginService.addAdmin(admin);
//            return ResponseEntity.ok(status);
//        } catch (InvalidInputException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
//        } catch (Exception e) {
//            return new ResponseEntity<>("An unexpected error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    