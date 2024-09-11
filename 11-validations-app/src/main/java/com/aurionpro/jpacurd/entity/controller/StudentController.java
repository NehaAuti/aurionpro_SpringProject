package com.aurionpro.jpacurd.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.aurionpro.jpacurd.dto.PageResponse;
import com.aurionpro.jpacurd.entity.Student;
import com.aurionpro.jpacurd.entity.service.StudentService;
import com.aurionpro.jpacurd.errors.StudentErrorResponse;
import com.aurionpro.jpacurd.exceptions.StudentNotFoundException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentapp")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@GetMapping("/student")
	public ResponseEntity<PageResponse<Student>> getAllStudents(@RequestParam int pageno, @RequestParam int pagesize)
	{
		return ResponseEntity.ok(studentService.getAllStudents(pageno,pagesize));
	}
	
    @GetMapping("student/{rollnumber}")
    public ResponseEntity<Student> getStudentByRollNumber(@PathVariable int rollnumber) 
    {
    	return ResponseEntity.ok(studentService.getByRollNumber(rollnumber));
    }
    

    @PostMapping("/student")
    public ResponseEntity<String> addStudent(@Valid @RequestBody Student student) 
    {
        studentService.addStudent(student);
        return ResponseEntity.ok("Student added successfully");
    }

    @DeleteMapping("student/{rollnumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable int rollnumber) 
    {      
    	 studentService.deleteStudent((int) rollnumber);
         return ResponseEntity.ok("Student deleted sucessfully");
    }
    
    @GetMapping("/students")
    public ResponseEntity<PageResponse<Student>> getAllStudentsByName(
        @RequestParam(required = false) String name,
        @RequestParam int pageno, 
        @RequestParam int pagesize)  
    {
        if (name != null) {
            return ResponseEntity.ok(studentService.getAllStudentsByName(name, pageno, pagesize));
        } else {
            return ResponseEntity.ok(studentService.getAllStudents(pageno, pagesize));
        }
    }

}





