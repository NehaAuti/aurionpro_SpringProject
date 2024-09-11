package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.StudentDto;
import com.aurionpro.mappings.entity.Address;
import com.aurionpro.mappings.entity.Student;

public interface StudentService {
	
	StudentDto addStundent(StudentDto student);

	Student addStundent(Student student);
	
	Address updateStudentAddress(int rollnumber, Address address);
	
	Address getStudentAddress(int rollnumber);
	PageResponse<StudentDto> getAllStudents (int pageNumber , int pageSize);
	StudentDto getStudentByRollNumber (int rollnumber);
	StudentDto assignCourses(int rollnumber,List<Integer> courseIds);
	
	List<StudentDto> getStudentsByInstructor(int instructorId);
}
