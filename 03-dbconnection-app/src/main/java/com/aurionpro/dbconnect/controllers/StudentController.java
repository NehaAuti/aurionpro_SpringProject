package com.aurionpro.dbconnect.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.dbconnect.entity.Student;
import com.aurionpro.dbconnect.service.StudentService;
@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student")
	public ResponseEntity<List<Student>> getAllStudents()
	{
		return ResponseEntity.ok(studentService.getAllStudents());
	}
	
	@GetMapping("/student/{rollnumber}")
	public ResponseEntity<Student> getStudent(@PathVariable int rollnumber)
	{
		return ResponseEntity.ok(studentService.getStudent(rollnumber));
	}
	
	
	@PostMapping("/student")
	public String addStudent(@RequestBody Student student)
	{
		studentService.addStudent(student);
		return "Record added successfully"; 
	}
	
	@GetMapping("/student/name")
	public ResponseEntity<List<Student>> getStudentByName(@RequestParam String name)
	{
		return ResponseEntity.ok(studentService.getStudentByName(name));
	}
	
	
	
}
