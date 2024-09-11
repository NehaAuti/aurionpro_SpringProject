package com.aurionpro.mappings.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class BankErrorResponse {
    private int statusCode;
    private String errorMessage;
    private long timestamp;
}
