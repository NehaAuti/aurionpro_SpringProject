package com.aurionpro.mappings.controller;

import java.util.Collections;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.dto.CustomerDto;
import com.aurionpro.mappings.dto.JwtAuthResponse;
import com.aurionpro.mappings.dto.LoginDTO;
import com.aurionpro.mappings.dto.RegistrationDTO;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.exception.CustomerNotFoundException;
import com.aurionpro.mappings.exception.InvalidInputException;
import com.aurionpro.mappings.exception.UserApiException;
import com.aurionpro.mappings.service.AuthService;
import com.aurionpro.mappings.service.CaptchaService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bankapp")
@Slf4j
public class AuthController {
	
	@Autowired
    private AuthService authService;

    @Autowired
    private  CaptchaService captchaService;

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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CustomerDto> addNewCustomer(@RequestBody CustomerDto customerDTO) {
        log.info("Received request to add new customer: {} {}", customerDTO.getFirstName(), customerDTO.getLastName());
        CustomerDto createdCustomer = authService.addNewCustomer(customerDTO);
        return ResponseEntity.ok(createdCustomer);
    }


    @GetMapping("/generate")
    public Map<String, String> generateCaptcha() {
        // Generate a CAPTCHA with 4 to 6 characters
        String captcha = captchaService.generateCaptcha(); 
        String captchaId = "CAPTCHA-" + System.currentTimeMillis(); // Generate a simple unique ID

        captchaService.storeCaptcha(captchaId, captcha); // Store the CAPTCHA with its ID

        // Return a structured response
        Map<String, String> response = new HashMap<>();
        response.put("captchaId", captchaId);
        response.put("captcha", captcha);
        
        return response; // Will return {"captchaId": "...", "captcha": "..."}
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDTO loginDto) {
        // Validate CAPTCHA
        boolean isCaptchaValid = captchaService.validateCaptcha(loginDto.getCaptchaId(), loginDto.getCaptcha());

        if (!isCaptchaValid) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("message", "Invalid CAPTCHA"));
        }

        try {
            // Perform the authentication logic
            String token = authService.login(loginDto);

            // Return the token in the response
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (UserApiException e) {
            return ResponseEntity.status(e.getStatus()).body(Collections.singletonMap("message", e.getMessage()));
        }
    }
}

    
	

