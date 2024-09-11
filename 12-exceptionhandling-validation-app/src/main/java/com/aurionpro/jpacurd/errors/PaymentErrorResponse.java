package com.aurionpro.jpacurd.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor    // Parameterized constructor
@NoArgsConstructor     // Default constructor
@Getter                // Getters
@Setter                // Setters
public class PaymentErrorResponse {

    private int statusCode;
    private String errorMessage;
    private long timestamp;
}