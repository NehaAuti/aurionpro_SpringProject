package com.aurionpro.mappings.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.service.CourseService;


@RestController
@RequestMapping("/studentapp")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/courses")
	public ResponseEntity<Course> addNewCourse(@RequestBody CourseDto courseDto)
	{
		return ResponseEntity.ok(courseService.addCourse(courseDto));
	}
	
	@GetMapping("/courses")
	public ResponseEntity<List<CourseDto>> addAllCourses()
	{
		return ResponseEntity.ok(courseService.getAllCourses());
	}
	
	@PostMapping("/courses/instructor")
	public ResponseEntity<CourseDto> assignInstructor(@RequestParam int courseId, @RequestParam int instructorid)
	{
		return ResponseEntity.ok(courseService.assignInstructor(courseId,instructorid));
	}
	
	@PutMapping("/courses/students")
	public ResponseEntity<Course> assignStudentsToCourse(
	        @RequestParam int courseId,
	        @RequestBody List<Integer> studentIds) {
	    Course updatedCourse = courseService.assignStudentsToCourse(courseId, studentIds);
	    return ResponseEntity.ok(updatedCourse);
	}
	
	@GetMapping("/courses/byinstructor")
    public ResponseEntity<List<Course>> getCoursesByInstructor(@RequestParam int instructorId) {
        List<Course> courses = courseService.getCoursesByInstructor(instructorId);
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(courses);
    }


}
