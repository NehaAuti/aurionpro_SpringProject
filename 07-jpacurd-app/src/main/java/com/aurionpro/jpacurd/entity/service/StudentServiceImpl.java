package com.aurionpro.jpacurd.entity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.jpacurd.entity.Student;
import com.aurionpro.jpacurd.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	
	@Override
	public List<Student> getAllStudents() {
		return studentRepo.findAll();
	}


	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepo.save(student);
		
	}


	@Override
	public Student getByRollNumber(int rollnumber) {
		// TODO Auto-generated method stub
		return studentRepo.getById(rollnumber);
		
	}


	@Override
	public void deleteStudent(int rollnumber) {
		// TODO Auto-generated method stub
		studentRepo.deleteById(rollnumber);
		
		
	}


	@Override
	public List<Student> getAllStudentsByName(String name) {
		// TODO Auto-generated method stub
		return studentRepo.findByName(name);
	}

}
