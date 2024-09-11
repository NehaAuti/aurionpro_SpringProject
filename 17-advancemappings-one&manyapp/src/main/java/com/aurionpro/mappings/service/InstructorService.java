package com.aurionpro.mappings.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.dto.InstructorDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.entity.Instructor;

public interface InstructorService {
	InstructorDto addInstructor(InstructorDto instructorDto);

	Instructor allocateInstructorToACourse(int courseId, int instructorId);
	 Instructor allocateCourses(int instructorId, List<Integer> courseIds);
	
	InstructorDto getInstructorDto (int instructorId);
	
	InstructorDto getInstructor(int instructorId);
	
	List<Course> getInstructorCourses(int instructorId);
	
	Page<InstructorDto> getAllInstructors(int pageno ,int pagesize);
	
	//List<Instructor> getAllInstructors();        // Add this method
    Instructor getInstructorById(int instructorId);  // Add this method

}
