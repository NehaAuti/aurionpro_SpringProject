package com.aurionpro.jpacurd.errors;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor    // Parameterized constructor
@NoArgsConstructor     // Default constructor
@Getter                // Getters
@Setter                // Setters
public class CustomerErrorResponse {

    private int statusCode;
    private String errorMessage;
    private long timestamp;
    private Map<String, String> errors; 
}