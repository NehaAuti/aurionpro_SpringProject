package com.aurionpro.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.dto.InstructorDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.entity.Instructor;

import com.aurionpro.mappings.service.InstructorService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentapp")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;

	 @PostMapping("/instructors")
	    public ResponseEntity<InstructorDto> addInstructor(@Valid @RequestBody InstructorDto instructorDto) 
	    {
	       
	        return ResponseEntity.ok( instructorService.addInstructor(instructorDto));
	    }
	 

   @PutMapping("/instructors/course")
   public ResponseEntity<Instructor> allocateCoursesToInstructor(
           @PathVariable int instructorId, 
           @RequestBody List<Integer> courseIds) {
       Instructor updatedInstructor = instructorService.allocateCourses(instructorId, courseIds);
       if (updatedInstructor != null) {
           return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
   }
	 
	 @PutMapping("instructors/courses")
	    public ResponseEntity<Instructor> allocateInstructorToACourse(
	            @RequestParam int courseId, 
	            @PathVariable int instructorId) {
	        
	        Instructor instructor = instructorService.allocateInstructorToACourse(courseId, instructorId);
	        
	        if (instructor != null) {
	            return new ResponseEntity<>(instructor, HttpStatus.OK);
	        }
	        
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Return 404 if the instructor or course was not found
	    }
	
	 
//	 @GetMapping("instructors/{id}/courses")
//	    public ResponseEntity<List<Course>> getInstructorCourses(@PathVariable int id) {
//	        List<Course> courses = instructorService.getInstructorCourses(id);
//	        if (!courses.isEmpty()) {
//	            return ResponseEntity.ok(courses);
//	        } else {
//	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(courses);
//	        }
//	    }
	 
	 

	 @GetMapping("/instructor")
	 public ResponseEntity<InstructorDto> getInstructor(@RequestParam int instructorId) {
	     InstructorDto instructorDto = instructorService.getInstructor(instructorId);
	     if (instructorDto != null) {
	         return new ResponseEntity<>(instructorDto, HttpStatus.OK);
	     }
	     return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Properly handle the not found case
	 }


	    @GetMapping("instructors/courses")
	    public ResponseEntity<List<Course>> getInstructorCourses(@RequestParam int instructorId) {
	        List<Course> courses = instructorService.getInstructorCourses(instructorId);
	        if (!courses.isEmpty()) {
	            return new ResponseEntity<>(courses, HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Handle not found case
	    }

//	    @GetMapping("/instructors")
//	    public ResponseEntity<Page<InstructorDto>> getAllInstructors(
//	            @RequestParam int pageno,
//	            @RequestParam int pagesize) {
//	        Page<InstructorDto> instructorPage = instructorService.getAllInstructors(pageno, pagesize);
//	        return new ResponseEntity<>(instructorPage, HttpStatus.OK);
//	    }
	    
	    @GetMapping("/instructors")
	    public ResponseEntity<Page<InstructorDto>> getAllInstructors(
	            @RequestParam int pageno, 
	            @RequestParam int pagesize) {
	        Page<InstructorDto> instructorsPage = instructorService.getAllInstructors(pageno, pagesize);
	        if (instructorsPage.hasContent()) {
	            return new ResponseEntity<>(instructorsPage, HttpStatus.OK);
	        }
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	 
	 

	 
//	    // Endpoint to get all instructors
//	    @GetMapping("/instructor")
//	    public ResponseEntity<List<Instructor>> getAllInstructors() {
//	        List<Instructor> instructors = instructorService.getAllInstructors();
//	        if (instructors.isEmpty()) {
//	            return ResponseEntity.noContent().build();
//	        }
//	        return ResponseEntity.ok(instructors);
//	    }

//	    // Endpoint to get an instructor by ID
//	    @GetMapping("instructor/{id}")
//	    public ResponseEntity<Instructor> getInstructorById(@PathVariable("id") int instructorId) {
//	        Instructor instructor = instructorService.getInstructorById(instructorId);
//	        if (instructor == null) {
//	            return ResponseEntity.notFound().build();
//	        }
//	        return ResponseEntity.ok(instructor);
//	    }
}
