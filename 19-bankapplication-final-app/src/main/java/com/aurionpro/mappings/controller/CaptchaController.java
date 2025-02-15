package com.aurionpro.mappings.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.entity.Captcha;
import com.aurionpro.mappings.service.CaptchaService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("bankapp/captcha")
public class CaptchaController {

    private final CaptchaService captchaService;

    public CaptchaController(CaptchaService captchaService) {
        this.captchaService = captchaService;
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

    @PostMapping("/validate")
    public ResponseEntity<String> validateCaptcha(
            @RequestParam String captchaId,
            @RequestParam String inputCaptcha) {
        Captcha storedCaptcha = captchaService.getCaptcha(captchaId);
        if (storedCaptcha != null && storedCaptcha.getInputCaptcha().equals(inputCaptcha)) {
            captchaService.removeCaptcha(captchaId); // Optionally remove CAPTCHA after validation
            return ResponseEntity.ok("Captcha is valid");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Captcha is invalid");
        }
    }
}