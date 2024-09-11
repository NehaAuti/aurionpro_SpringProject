package com.aurionpro.dbconnect.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.dbconnect.entity.Student;
import com.aurionpro.dbconnect.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {
     
	@Autowired
	private StudentRepository studentRepo;

	
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepo.getAllStudents();
	}


	@Override
	public Student getStudent(int rollnumber) {
		// TODO Auto-generated method stub
		return studentRepo.getStudent(rollnumber);
	}


	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepo.addStudent(student);
	}


	@Override
	public List<Student> getStudentByName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.getStudentByName(name);
	}


}
