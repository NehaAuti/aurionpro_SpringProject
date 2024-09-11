package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.ISBN;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.CourseDto;
import com.aurionpro.mappings.dto.InstructorDto;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.entity.Instructor;
import com.aurionpro.mappings.repository.CourseRepository;
import com.aurionpro.mappings.repository.InstructorRepository;
@Service
public class InstructorServiceImpl implements InstructorService{


	@Autowired
	private InstructorRepository instructorRepo;
	@Autowired
	private CourseRepository courseRepo;
	
	@Override
	public InstructorDto addInstructor(InstructorDto instructorDto)  {
		// TODO Auto-generated method stub
		
		Instructor instructor = toInstructorMapper(instructorDto);
		
		Instructor instructorDb=instructorRepo.save(instructor);
		
		return (toInstructorDtoMapper(instructor));
	}
	
	private Instructor toInstructorMapper(InstructorDto instructorDto)
	{
		Instructor instructor = new Instructor();
		instructor.setInstructorName(instructorDto.getInstructorName());
		instructor.setEmail(instructorDto.getEmail());
		instructor.setQualification(instructorDto.getQualification());
		return instructor;
	}
	
	private InstructorDto toInstructorDtoMapper (Instructor instructor)
	{
		InstructorDto instructorDto=new InstructorDto();
		
		instructorDto.setInstructorId(instructor.getInstructorId());
		instructorDto.setEmail(instructor.getEmail());
		instructorDto.setInstructorName(instructor.getInstructorName());
		instructorDto.setQualification(instructor.getQualification());
		
		return instructorDto;
		
	}
	
//	@Override
//	 public Instructor allocateCourses(int instructorId, List<Course> courses) {
//	  Instructor dbInstructor;
//	  Optional<Instructor> optionalInstructor=instructorRepo.findById(instructorId);
//	  if(!optionalInstructor.isPresent())
//	   return null;
//	  dbInstructor=optionalInstructor.get();
//	  
//	  List<Course> dbCourses = dbInstructor.getCourses();
//	  
//	  // List<Course> toUpdateCourses=new ArrayList<>();
//	  
//	  courses.forEach((course)->{
//	   //course.setInstructor(dbInstructor);
//	   
//	   Course temp=courseRepo.findById(course.getCourseId()).get();
//	   temp.setInstructor(dbInstructor);
//	   dbCourses.add(temp);
//	  });
//	  dbInstructor.setCourses(dbCourses);
//	  
//	  return instructorRepo.save(dbInstructor);
//	 }
//	
    
	
	@Override

	public Instructor allocateCourses(int instructorId, List<Integer> courseIds) {
	    Instructor dbInstructor = getInstructorById(instructorId);
	    if (dbInstructor == null) {
	        return null; // Handle the case where the instructor is not found
	    }

	    List<Course> dbCourses = getInstructorCourses(instructorId);
	    List<Course> coursesToAdd = new ArrayList<>();

	    for (Integer courseId : courseIds) {
	        Course course = courseRepo.findById(courseId).orElse(null);
	        if (course != null) {
	            course.setInstructor(dbInstructor);
	            coursesToAdd.add(course);
	        }
	    }

	    dbCourses.addAll(coursesToAdd);
	    dbInstructor.setCourses(dbCourses);

	    return instructorRepo.save(dbInstructor);
	}

	
	@Override
	 public Instructor allocateInstructorToACourse(int courseId, int instructorId) { 
		 
        Optional<Course> optionalCourse = courseRepo.findById(courseId);  //Fetch the course from the database using the courseId 
        if (!optionalCourse.isPresent()) { 
            return null;                             //Or throw an exception 
        } 
         
        Course dbCourse = optionalCourse.get(); 
         
        //Fetch the instructor from the database using the instructorId 
         
        Optional<Instructor> optionalInstructor = instructorRepo.findById(instructorId);  
        if (!optionalInstructor.isPresent()) { 
            return null;                             // Or throw an exception 
        } 
        Instructor dbInstructor = optionalInstructor.get(); 
 
        // Assign the instructorId to the course 
        dbCourse.setInstructor(dbInstructor); 
         
        // Save the updated course back to the database 
        courseRepo.save(dbCourse); 
         
        // Return the instructor for confirmation 
        return dbInstructor; 
    } 
	
//	@Override
//    public List<Instructor> getAllInstructors() {
//        return instructorRepo.findAll(); // Fetch and return all instructors
//    }

    @Override
    public Instructor getInstructorById(int instructorId) {
        Optional<Instructor> instructorOpt = instructorRepo.findById(instructorId);
        return instructorOpt.orElse(null); // Return instructor if found, otherwise return null
}

    @Override
    public InstructorDto getInstructor(int instructorId) {
        Instructor instructor = getInstructorById(instructorId);
        if (instructor != null) {
            return toInstructorDtoMapper(instructor);
        }
        return null; // Return null or handle not found case appropriately
//    return toInstructorDtoMapper(getInstructor(instructorId));
    }

    @Override
    public List<Course> getInstructorCourses(int instructorId) {
        Optional<Instructor> instructorOpt = instructorRepo.findById(instructorId);
        if (instructorOpt.isPresent()) {
            Instructor instructor = instructorOpt.get();
            return instructor.getCourses(); // Assuming Instructor has a method getCourses()
        }
        return Collections.emptyList(); // Return an empty list if instructor is not found
    }

    @Override
    public Page<InstructorDto> getAllInstructors(int pageno, int pagesize) {
        Pageable pageable = PageRequest.of(pageno, pagesize);
        Page<Instructor> instructorsPage = instructorRepo.findAll(pageable);

        // Create a list to hold the converted InstructorDto objects
        List<InstructorDto> instructorDtoList = new ArrayList<>();

        // Convert each Instructor to InstructorDto and add to the list
        for (Instructor instructor : instructorsPage.getContent()) {
            InstructorDto instructorDto = toInstructorDtoMapper(instructor);
            instructorDtoList.add(instructorDto);
        }

        // Create a new Page object with the converted InstructorDto list
        return new PageImpl<>(instructorDtoList, pageable, instructorsPage.getTotalElements());
    }

	@Override
	public InstructorDto getInstructorDto(int instructorId) {
		// TODO Auto-generated method stub
		Instructor instructor = getInstructorById(instructorId);
	    if (instructor != null) {
	        return toInstructorDtoMapper(instructor);
	    }
	    return null; // Return null or handle the not found case as needed
	}


}

