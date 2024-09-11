package com.aurionpro.mappings.service;

import java.util.List;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.entity.Course;

public interface CourseService {

	Course addNewCourse(CourseDto courseDto);
    Course allocateInstructorToCourse(int courseId, int instructorId);
    List<Course> getAllCourses();         // Add this method to the interface
    Course getCourseById(int courseId);   // Add this method to the interface
}
