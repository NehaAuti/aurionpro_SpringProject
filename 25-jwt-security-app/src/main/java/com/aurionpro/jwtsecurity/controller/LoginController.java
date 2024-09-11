package com.aurionpro.jwtsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.jwtsecurity.dto.JwtAuthResponse;
import com.aurionpro.jwtsecurity.dto.LoginDTO;
import com.aurionpro.jwtsecurity.dto.RegistrationDTO;
import com.aurionpro.jwtsecurity.entity.User;
import com.aurionpro.jwtsecurity.services.AuthService;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegistrationDTO registerDto) {
        return ResponseEntity.ok(authService.register(registerDto));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDTO loginDto) {
        String token = authService.login(loginDto);
        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);
        return ResponseEntity.ok(jwtAuthResponse);
    }
}