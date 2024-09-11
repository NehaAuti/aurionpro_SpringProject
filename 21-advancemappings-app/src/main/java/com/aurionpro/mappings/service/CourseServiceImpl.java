package com.aurionpro.mappings.service;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.entity.Instructor;
import com.aurionpro.mappings.entity.Student;
import com.aurionpro.mappings.repository.CourseRepository;
import com.aurionpro.mappings.repository.InstructorRepository;
import com.aurionpro.mappings.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CourseServiceImpl implements CourseService{
	
	@Autowired
	private CourseRepository courseRepo;
	@Autowired
	private InstructorRepository instructorRepo;
	
	@Autowired
	private StudentRepository studentRepo;

	private static final Logger logger =LoggerFactory.getLogger(CourseServiceImpl.class);
	
	@Override
	public Course addCourse(CourseDto coursedto) {
		  
		  Course course = new Course();
		  course.setCourseName(coursedto.getCourseName());
		  course.setDuration(coursedto.getDuration());
		  course.setFees(coursedto.getFees());
		  course=courseRepo.save(course);
		  logger.info("Course added Sucessfully"+course.getCourseId());
		  return course;
		 }
	
//	@Override
//	public CourseDto addCourse(CourseDto courseDto) {
//	
//		Course course = toCourseMapper(courseDto);
//		
//		course=courseRepo.save(course);
//		
//		return toCourseDtoMapper(course);
//	}
	
	private Course toCourseMapper(CourseDto courseDto)
	{
		Course course=new Course();
		course.setDuration(courseDto.getDuration());
		course.setFees(courseDto.getFees());
		course.setCourseName(courseDto.getCourseName());
		return course;
	}
	
	private CourseDto toCourseDtoMapper(Course course)
	{
		CourseDto courseDto=new CourseDto();
		courseDto.setDuration(course.getDuration());
		courseDto.setFees(course.getFees());
		courseDto.setCourseName(course.getCourseName());
		courseDto.setCourseId(course.getCourseId());
		return courseDto;
	}

	@Override
	public List<CourseDto> getAllCourses() {
		
		List<Course> courses=courseRepo.findAll();
		if(courses.size()==0)
		return null;
		
		List<CourseDto> courseDtos=new ArrayList<>();
		
		courses.forEach((course)->{
			courseDtos.add(toCourseDtoMapper(course));
		});
		
		return courseDtos;
	}

	@Override
	public CourseDto getCourseById(int courseId) {

	        Course course=null;
	        
	        Optional<Course> optionalCourse=courseRepo.findById(courseId);
	        if(!optionalCourse.isPresent())
	        {
	         logger.error("Course with id: "+courseId+" not found");
	     return null;
	        }
	        course=optionalCourse.get();
	        return toCourseDtoMapper(course);
	 }

	@Override
	public CourseDto assignInstructor(int courseId, int instructorid) {

		Course dbCourse=toCourseMapper(getCourseById(courseId));
		dbCourse.setCourseId(courseId);	
	
		
		Instructor instructor=instructorRepo.findById(instructorid)
				              .orElseThrow(()-> new NullPointerException("Instructor Not Found"));
		
		dbCourse.setInstructor(instructor);
		
		dbCourse=courseRepo.save(dbCourse);
		
         
		return toCourseDtoMapper(dbCourse);
	}
	
	
	
	@Override
	public Course assignStudentsToCourse(int courseId, List<Integer> studentIds) {
	    Course dbCourse = courseRepo.findById(courseId)
	            .orElseThrow(() -> new RuntimeException("Course not found"));

	    List<Student> existingStudents = dbCourse.getStudents();
	    if (existingStudents == null) {
	        existingStudents = new ArrayList<>();
	    }

	    List<Student> studentsToAdd = new ArrayList<>();
	    studentIds.forEach(id -> {
	        Student student = studentRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Student not found"));
	        studentsToAdd.add(student);
	    });

	    // Add new students to the existing list
	    existingStudents.addAll(studentsToAdd);
	    dbCourse.setStudents(existingStudents);

	    return courseRepo.save(dbCourse);
	}
	
	@Override
    public List<Course> getCoursesByInstructor(int instructorId) {
        return courseRepo.findByInstructorInstructorId(instructorId);
    }


	
	

}
