package com.aurionpro.mappings.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.StudentDto;
import com.aurionpro.mappings.entity.Address;
import com.aurionpro.mappings.entity.Course;
import com.aurionpro.mappings.entity.Student;
import com.aurionpro.mappings.repository.CourseRepository;
import com.aurionpro.mappings.repository.StudentRepository;



@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	private StudentRepository studentRepo;
	
	@Autowired
	private CourseRepository courseRepo;

	@Override
	public Student addStundent(Student student) {
		
		return studentRepo.save(student);
	}
	
	@Override
	public StudentDto addStundent(StudentDto studentDto) {
	
		Student student = toStudentMapper(studentDto);
		
		student = studentRepo.save(student);
	return toStudentDtoMapper(student);
}
	

	
	private StudentDto toStudentDtoMapper(Student student)
	{
		StudentDto studentDto = new StudentDto();
	
		studentDto.setAge(student.getAge());
		studentDto.setName(student.getName());
		studentDto.setRollnumber(student.getRollnumber());
		
		return studentDto;
		
	}
	
	
	private Student toStudentMapper(StudentDto studentDto)
	{
		
		Student student = new Student();
	
		student.setAge(studentDto.getAge());
		student.setName(studentDto.getName());
		//studentDto.setRollnumber(student.getRollnumber());
		
		return student;
		
	}
	
	@Override
	public Address updateStudentAddress(int rollnumber, Address address) {
	    Optional<Student> optionalStudent = studentRepo.findById(rollnumber);
	    
	    if (!optionalStudent.isPresent()) {
	        throw new RuntimeException("Student not found with roll number: " + rollnumber);
	    }

	    Student dbStudent = optionalStudent.get();
	    Address dbAddress = dbStudent.getAddress();

	    if (dbAddress == null) {
	        dbAddress = new Address(); // Create a new Address if it's null
	    }

	    // Update each field of the address if it's provided in the request
	    if (address.getCity() != null) {
	        dbAddress.setCity(address.getCity());
	    }
	    if (address.getBuildingName() != null) {
	        dbAddress.setBuildingName(address.getBuildingName());
	    }
	    if (address.getAreaName() != null) {
	        dbAddress.setAreaName(address.getAreaName());
	    }
	    if (address.getPincode() != 0) {
	        dbAddress.setPincode(address.getPincode());
	    }

	    dbStudent.setAddress(dbAddress);
	    studentRepo.save(dbStudent);

	    return dbAddress;
	}

	
	




	@Override
	public Address getStudentAddress(int rollnumber) {
	    // Find the student by roll number
	    Optional<Student> studentOpt = studentRepo.findById(rollnumber);
	    
	    // If student is present, return their address
	    if (studentOpt.isPresent()) {
	        return studentOpt.get().getAddress();
	    } else {
	        throw new RuntimeException("Student with roll number " + rollnumber + " not found");
	    }
	}




	@Override
	public PageResponse<StudentDto> getAllStudents(int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page <Student> studentPage=studentRepo.findAll(pageable);
		
		PageResponse <StudentDto> response = new PageResponse();
		response.setTotalPages(studentPage.getTotalPages());
		response.setTotalElements(studentPage.getTotalElements());
		response.setSize(studentPage.getSize());
		response.setLastPage(studentPage.isLast());
		
		List<StudentDto> studentDtoList = new ArrayList<>();
		
		studentPage.getContent().forEach((student)->{
			
			 studentDtoList.add(toStudentDtoMapper(student));
		});
		
		response.setContents(studentDtoList);
		
		
		return response;
	}



	@Override
	public StudentDto getStudentByRollNumber(int rollnumber) {
		// TODO Auto-generated method stub
		Student student = studentRepo.findById(rollnumber).orElseThrow(
				()-> new NullPointerException("student not found"));
		return toStudentDtoMapper(student);
	}



	@Override
	public StudentDto assignCourses(int rollnumber, List<Integer> courseIds) {
		// TODO Auto-generated method stub
		
		Student dbStudent = studentRepo.findById(rollnumber).orElseThrow(()->new NullPointerException());
				//(getStudentByRollNumber(rollnumber));
        dbStudent.setRollnumber(rollnumber);
        
        Set<Course> exsistingCourses = dbStudent.getCourses();
        if(exsistingCourses == null)
        	exsistingCourses=new HashSet<>();
        
        Set<Course> courseToAdd = new HashSet<>();
        
        courseIds.forEach((id)->{
        	Course course = courseRepo.findById(id)
        			.orElseThrow(()->new NullPointerException());
        List<Student> existingStudents = course.getStudents();
        
        if(existingStudents == null)
        	existingStudents=new ArrayList<>();
        
        existingStudents.add(dbStudent);
        
        courseToAdd.add(course);
        });
        
        exsistingCourses.addAll(courseToAdd);
        dbStudent.setCourses(exsistingCourses);
        return toStudentDtoMapper(studentRepo.save(dbStudent));
	}
	
	@Override
    public List<StudentDto> getStudentsByInstructor(int instructorId) {
        List<Student> students = studentRepo.findByCoursesInstructorInstructorId(instructorId);
        return students.stream().map(student -> new StudentDto(
                student.getRollnumber(),
                student.getName(),
                student.getAge()
        )).collect(Collectors.toList());
    }

}
