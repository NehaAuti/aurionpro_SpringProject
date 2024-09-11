package com.aurionpro.mappings.error;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

	private String error;
    private String message;
}
