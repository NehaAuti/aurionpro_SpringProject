package com.aurionpro.mappings.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoginDTO {
	
   
    private String username;
    private String password;
    private String captcha; // Make sure this matches the field name in your JSON request
    private String captchaId;


}
