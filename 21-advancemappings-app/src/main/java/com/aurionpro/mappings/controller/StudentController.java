package com.aurionpro.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.StudentDto;
import com.aurionpro.mappings.entity.Address;
import com.aurionpro.mappings.entity.Student;
import com.aurionpro.mappings.service.StudentService;



@RestController
@RequestMapping("/studentapp")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/student")
	public ResponseEntity<Student> addNewStudent(@RequestBody Student student)
	{
		return ResponseEntity.ok(studentService.addStundent(student));
	}
	
	
	
	@PostMapping("/students")
	public ResponseEntity<StudentDto> addNewStudent(@RequestBody StudentDto studentDto)
	{
		return ResponseEntity.ok(studentService.addStundent(studentDto));
	}
	
	   // Endpoint to get the address of a student by roll number
   
	@GetMapping("/students")
	public ResponseEntity<PageResponse<StudentDto>> getAllStudents(@RequestParam int pageNumber,@RequestParam int pageSize)
	{
		return ResponseEntity.ok(studentService.getAllStudents(pageNumber,pageSize));
	}
	
	@GetMapping("/students/{rollnumber}")
	public ResponseEntity<StudentDto> getStudents(@PathVariable int rollnumber)
	{
		return ResponseEntity.ok(studentService.getStudentByRollNumber(rollnumber));
	}
	
	@GetMapping("/student/address")
	public Address getStudentAddress(@RequestParam int rollnumber) {
	    return studentService.getStudentAddress(rollnumber);
	}
	
	
	@PutMapping("/students/address")
	public ResponseEntity<Address> updateStudentAddress(@RequestParam int rollnumber, @RequestBody Address address ) {

	    Address updatedAddress = studentService.updateStudentAddress(rollnumber, address);
	    
	    if (updatedAddress == null) {
	        return ResponseEntity.notFound().build();
	    }
	    
	    return ResponseEntity.ok(updatedAddress);
	}

	
	@PostMapping("/{rollnumber}/assigncourses")
    public ResponseEntity<StudentDto> assignCourses(
            @PathVariable int rollnumber,
            @RequestBody List<Integer> courseIds) {
        
        StudentDto updatedStudent = studentService.assignCourses(rollnumber, courseIds);
        return ResponseEntity.ok(updatedStudent);
    }
	
	
	@GetMapping("/students/byinstructor")
    public ResponseEntity<List<StudentDto>> getStudentsByInstructor(@RequestParam int instructorId) {
        List<StudentDto> students = studentService.getStudentsByInstructor(instructorId);
        if (students.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(students);
    }
	

}
