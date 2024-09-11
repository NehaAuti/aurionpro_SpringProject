package com.aurionpro.mappings.controller;

import java.util.List;
import java.util.Map;

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

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.service.CourseService;
import com.aurionpro.mappings.service.InstructorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/studentapp")
public class CourseController {
	
	@Autowired
	private CourseService courseService;

	 @PostMapping("/courses")
	    public ResponseEntity<Course> addNewCourse(@Valid @RequestBody CourseDto courseDto) 
	    {
	       
	        return ResponseEntity.ok( courseService.addNewCourse(courseDto));
	    }
	@PutMapping("/courses/instructors")
	
	public Course allocateInstructor(@RequestBody Map<String, Integer> requestData) {
	    int courseId = requestData.get("courseId");
	    int instructorId = requestData.get("instructorId");
	    return courseService.allocateInstructorToCourse(courseId, instructorId);
	}
	@GetMapping("/courses")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable("id") int courseId) {
        Course course = courseService.getCourseById(courseId);
        if (course != null) {
            return ResponseEntity.ok(course);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
