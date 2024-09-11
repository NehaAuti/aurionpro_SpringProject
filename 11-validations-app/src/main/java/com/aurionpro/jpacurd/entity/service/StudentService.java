package com.aurionpro.jpacurd.entity.service;

import com.aurionpro.jpacurd.dto.PageResponse;
import com.aurionpro.jpacurd.entity.Student;

public interface StudentService {
	
	PageResponse<Student> getAllStudents(int pageno,int pagesize);
	void addStudent(Student student);
	Student getByRollNumber(int rollnumber);
	void deleteStudent(int rollnumber);
	PageResponse<Student> getAllStudentsByName(String name,int pageno,int pagesize);

}
