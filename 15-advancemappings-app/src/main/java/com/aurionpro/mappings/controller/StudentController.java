package com.aurionpro.mappings.controller;

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

import com.aurionpro.mappings.dto.AddressDTO;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.StudentDTO;
import com.aurionpro.mappings.entity.Address;
import com.aurionpro.mappings.entity.Student;
import com.aurionpro.mappings.service.StudentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentapp")
public class StudentController {
	
	@Autowired
	private StudentService studentService;

	 @PostMapping("/student")
	    public ResponseEntity<String> addStudent(@Valid @RequestBody Student student) 
	    {
	        studentService.addStudent(student);
	        return ResponseEntity.ok("Student added successfully");
	    }
	 
	 @GetMapping("/student")
		public ResponseEntity<PageResponse<Student>> getAllStudents(@RequestParam int pageno, @RequestParam int pagesize)
		{
			return ResponseEntity.ok(studentService.getAllStudents(pageno,pagesize));
		}
	 
	 
	// Endpoint to get basic student data (roll number, name, age)
	    @GetMapping("student/detail")
	    public ResponseEntity<StudentDTO> getStudentByRollnumber(@RequestParam int rollnumber) {
	        StudentDTO studentdto = studentService.getStudentByRollnumber(rollnumber);
	        return ResponseEntity.ok(studentdto);
	    }

	    // Endpoint to get student's address by rollnumber
	    @GetMapping("student/{rollnumber}/address")
	    public ResponseEntity<Student> getStudentWithAddressByRollnumber(@PathVariable int rollnumber) {
	        Student student = studentService.getStudentWithAddressByRollnumber(rollnumber);
	        return ResponseEntity.ok(student);
	    }
	    
	 // Endpoint to get student's address by roll number
	    @GetMapping("student/address")
	    public ResponseEntity<Address> getStudentAddressByRollnumber(@RequestParam int rollnumber) {
	        Address address = studentService.getStudentAddressByRollnumber(rollnumber);
	        return ResponseEntity.ok(address);
	        
	        
	    }
	    
	    @PostMapping("student/address/update")
	    public ResponseEntity<String> partiallyUpdateStudentAddress(@RequestParam int rollnumber, @RequestBody AddressDTO addressDTO) {
	        boolean isUpdated = studentService.partiallyUpdateStudentAddress(rollnumber, addressDTO);
	        if (isUpdated) {
	            return ResponseEntity.ok("Address updated successfully");
	        } else {
	            return ResponseEntity.status(404).body("Student not found");
	        }
	    }
}
