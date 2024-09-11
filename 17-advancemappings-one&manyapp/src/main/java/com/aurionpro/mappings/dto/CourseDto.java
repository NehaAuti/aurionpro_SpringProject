package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor    //parameter constructor
@NoArgsConstructor     //default constructor
@Data

public class CourseDto {

    private int courseId;

    
    private String courseName;

    
    private String duration;

    
    private String fees;

}
