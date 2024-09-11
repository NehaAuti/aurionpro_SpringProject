package com.aurionpro.mappings.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String errorMessage;
    private long timestamp;
}
