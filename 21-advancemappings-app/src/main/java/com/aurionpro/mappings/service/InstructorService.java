package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.InstructorDto;
import com.aurionpro.mappings.dto.PageResponse;

public interface InstructorService {
	
	InstructorDto addInstrcutor(InstructorDto instructorDto);
	InstructorDto getInstructorById(int instructorId);
	PageResponse<InstructorDto> getAllInstructors(int pageNumber, int pageSize);
	InstructorDto allocateCourses(int instructorId, List<Integer> courseIds);

}
