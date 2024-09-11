package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.AddressDTO;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.StudentDTO;
import com.aurionpro.mappings.entity.Address;
import com.aurionpro.mappings.entity.Student;

public interface StudentService {

	PageResponse<Student> getAllStudents(int pageno,int pagesize);
	Student addStudent(Student student);
	StudentDTO getStudentByRollnumber(int rollnumber);
	Student getStudentWithAddressByRollnumber(int rollnumber);
	Address getStudentAddressByRollnumber(int rollnumber);
	boolean partiallyUpdateStudentAddress(int rollnumber, AddressDTO addressDTO);
	
}
