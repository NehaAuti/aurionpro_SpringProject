package com.aurionpro.mappings.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseDto {
	
	private int courseId;
	
	private String courseName;

	private int duration;
	
	private double fees;	

}
