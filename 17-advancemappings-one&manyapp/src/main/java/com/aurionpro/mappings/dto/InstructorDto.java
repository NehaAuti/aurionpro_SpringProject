package com.aurionpro.mappings.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor    //parameter constructor
@NoArgsConstructor     //default constructor
@Data
public class InstructorDto {
	
	
	private int instructorId;
	
	private String instructorName;
	
	private String email;
	
	private String qualification; 

}
