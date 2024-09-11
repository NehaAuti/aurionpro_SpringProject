package com.aurionpro.jpacurd.entity.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.aurionpro.jpacurd.dto.PageResponse;
import com.aurionpro.jpacurd.entity.Student;
import com.aurionpro.jpacurd.errors.StudentErrorResponse;
import com.aurionpro.jpacurd.exceptions.StudentNotFoundException;
import com.aurionpro.jpacurd.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepo;
	
	
//	@Override
//	public Page<Student> getAllStudents(int pageno , int pagesize) {
//		Pageable pageable = PageRequest.of(pageno,pagesize);
//		Page<Student> studentPage = studentRepo.findAll(pageable);
//		return studentPage;
//	}
	
	@Override
	public PageResponse<Student> getAllStudents(int pageno , int pagesize) {
		Pageable pageable = PageRequest.of(pageno,pagesize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponse studentPageResponse = new PageResponse();
		studentPageResponse.setTotalPages(studentPage.getTotalPages());
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setLastPage(studentPage.isLast());
		studentPageResponse.setContent(studentPage.getContent());
		return studentPageResponse;
	}


	@Override
	public void addStudent(Student student) {
		// TODO Auto-generated method stub
		studentRepo.save(student);
		
	}


	@Override
	public Student getByRollNumber(int rollnumber) {
	//	 TODO Auto-generated method stub
		//Student student = null;
		Optional<Student> dbStudent = studentRepo.findById(rollnumber);
		if(!dbStudent.isPresent())
			
//		 StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
//		  studentErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
		  
		{
	throw new StudentNotFoundException();

		}
		//throw new StudentNotFoundException(HttpStatus.BAD_REQUEST.value(),);
			//Student student=dbStudent.get();
		return dbStudent.get();
		//return student;
		//return studentRepo.getById(rollnumber);
		
	}


	@Override
	public void deleteStudent(int rollnumber) {
		// TODO Auto-generated method stub
		studentRepo.deleteById(rollnumber);
		
		
	}


	


	@Override
	public PageResponse<Student> getAllStudentsByName(String name, int pageno, int pagesize) {
	    Pageable pageable = PageRequest.of(pageno, pagesize);
	    Page<Student> studentPage = studentRepo.findByName(name, pageable);

	    PageResponse<Student> studentPageResponse = new PageResponse<>();
	    studentPageResponse.setTotalPages(studentPage.getTotalPages());
	    studentPageResponse.setSize(studentPage.getSize());
	    studentPageResponse.setTotalElements(studentPage.getTotalElements());
	    studentPageResponse.setLastPage(studentPage.isLast());
	    studentPageResponse.setContent(studentPage.getContent());
	    return studentPageResponse;
	}


}
