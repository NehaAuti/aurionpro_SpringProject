package com.aurionpro.mappings.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class SalaryAccountErrorResponse {
    private int statusCode;
    private String errorMessage;
    private long timestamp;
}
