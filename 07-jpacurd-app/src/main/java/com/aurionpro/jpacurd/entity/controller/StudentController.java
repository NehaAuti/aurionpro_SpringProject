package com.aurionpro.jpacurd.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.jpacurd.entity.Student;
import com.aurionpro.jpacurd.entity.service.StudentService;

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
	
    @GetMapping("student/{rollnumber}")
    public ResponseEntity<Student> getStudentByRollNumber(@PathVariable int rollnumber) 
    {
//        Student student = studentService.getByRollNumber(rollnumber);
//        if (student != null) {
//            return ResponseEntity.ok((List.of(student));
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    	
    	return ResponseEntity.ok(studentService.getByRollNumber(rollnumber));
    }

    @PostMapping("/student")
    public ResponseEntity<String> addStudent(@RequestBody Student student) 
    {
        studentService.addStudent(student);
        return ResponseEntity.ok("Student added successfully");
    }

    @DeleteMapping("student/{rollnumber}")
    public ResponseEntity<String> deleteStudent(@PathVariable int rollnumber) 
    {
//        Student student = studentService.getByRollNumber(rollnumber);
//        if (student != null) {
//            studentService.deleteStudent(rollnumber);
//            return ResponseEntity.ok("Student deleted successfully");
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    	 studentService.deleteStudent((int) rollnumber);
         return ResponseEntity.ok("Student deleted sucessfully");
    }
    
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudentsByName(@RequestParam(required = false) String name)  
    {
    	if(name!=null)
    		return ResponseEntity.ok(studentService.getAllStudentsByName(name));
    	return ResponseEntity.ok(studentService.getAllStudents());
    }
}





