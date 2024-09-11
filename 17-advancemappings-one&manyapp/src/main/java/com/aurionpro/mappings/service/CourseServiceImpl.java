package com.aurionpro.mappings.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.entity.Instructor;
import com.aurionpro.mappings.repository.CourseRepository;
import com.aurionpro.mappings.repository.InstructorRepository;
@Service
public class CourseServiceImpl implements CourseService {

	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private InstructorRepository instructorRepo;
	@Override
	public Course addNewCourse(CourseDto courseDto) {
		// TODO Auto-generated method stub
		Course course = new Course();
		course.setCourseName(courseDto.getCourseName());
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		return  courseRepo.save(course);
	}
	
	 public Course allocateInstructorToCourse(int courseId, int instructorId) {
	        Optional<Course> courseOpt = courseRepo.findById(courseId);
	        Optional<Instructor> instructorOpt = instructorRepo.findById(instructorId);

	        if (courseOpt.isPresent() && instructorOpt.isPresent()) {
	            Course course = courseOpt.get();
	            Instructor instructor = instructorOpt.get();

	            course.setInstructor(instructor);
	            return courseRepo.save(course);
	        }

	        // If course or instructor is not found, return null
	        return null;
	    }
	 // Method to get all courses
	    public List<Course> getAllCourses() {
	        return courseRepo.findAll();
	    }

	    // Method to get a course by its ID
	    public Course getCourseById(int courseId) {
	        Optional<Course> courseOpt = courseRepo.findById(courseId);
	        return courseOpt.orElse(null); // Return course if found, otherwise return null
	    }

}
