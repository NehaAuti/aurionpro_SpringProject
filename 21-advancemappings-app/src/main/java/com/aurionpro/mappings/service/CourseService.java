package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.entity.Course;


public interface CourseService {
	
	//CourseDto addCourse(CourseDto courseDto);

	Course addCourse(CourseDto coursedto);
	List<CourseDto> getAllCourses();
	
	CourseDto getCourseById(int courseId);

	CourseDto assignInstructor(int courseId, int instructorid);
	
	Course assignStudentsToCourse(int courseId, List<Integer> studentIds);
	
	List<Course> getCoursesByInstructor(int instructorId);
}
