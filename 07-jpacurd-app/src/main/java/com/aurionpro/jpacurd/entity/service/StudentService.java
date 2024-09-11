package com.aurionpro.jpacurd.entity.service;

import java.util.List;

import com.aurionpro.jpacurd.entity.Student;

public interface StudentService {
	
	List<Student> getAllStudents();
	void addStudent(Student student);
	Student getByRollNumber(int rollnumber);
	void deleteStudent(int rollnumber);
	List<Student> getAllStudentsByName(String name);

}
