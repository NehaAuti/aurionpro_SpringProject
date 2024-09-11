package com.aurionpro.jpacurd.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor    //parameter constructor
@NoArgsConstructor     //default constructor
@Getter                //getters
@Setter  
public class StudentErrorResponse {

	private int statusCode;
	private String errorMessage;
	private long timestamp;
}
