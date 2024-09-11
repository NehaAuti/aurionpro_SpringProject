package com.aurionpro.mappings.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.entity.Address;
import com.aurionpro.mappings.entity.Student;
import com.aurionpro.mappings.repository.StudentRepository;
import com.aurionpro.mappings.dto.AddressDTO;
import com.aurionpro.mappings.dto.PageResponse;
import com.aurionpro.mappings.dto.StudentDTO;
@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentRepository studentRepo;
	@Override
	public Student addStudent(Student student) {
		return studentRepo.save(student);
	}
	@Override
	public PageResponse<Student> getAllStudents(int pageno , int pagesize) {
		Pageable pageable = PageRequest.of(pageno,pagesize);
		Page<Student> studentPage = studentRepo.findAll(pageable);
		
		PageResponse studentPageResponse = new PageResponse();
		studentPageResponse.setTotalPages(studentPage.getTotalPages());
		studentPageResponse.setSize(studentPage.getSize());
		studentPageResponse.setTotalElements(studentPage.getTotalElements());
		studentPageResponse.setLastPage(studentPage.isLast());
		studentPageResponse.setContent(studentPage.getContent());
		return studentPageResponse;
	}
	
	public StudentDTO getStudentByRollnumber(int rollnumber) {
        Student student = studentRepo.findByRollnumber(rollnumber);
        return new StudentDTO(student.getRollnumber(), student.getName(), student.getAge());
    }

    public Student getStudentWithAddressByRollnumber(int rollnumber) {
        return studentRepo.findByRollnumber(rollnumber);
    }

    public Address getStudentAddressByRollnumber(int rollnumber) {
        Student student = studentRepo.findByRollnumber(rollnumber);
        return student.getAddress();
        
//        Student dbStudent = null;
//        Optional <Student> optionalStudent = studentRepo.findById(rollnumber);
//        if(!optionalStudent.isPresent())
//        	return null;
//        
//        dbStudent=optionalStudent.get();
//        return dbStudent.getAddress();
    }
    public boolean partiallyUpdateStudentAddress(int rollnumber, AddressDTO addressDTO) {
        // Find the student by roll number
        Student student = studentRepo.findByRollnumber(rollnumber);
        if (student != null) {
            // Get the associated address
            Address address = student.getAddress();
            
            // Update only the fields provided in the AddressDTO
            if (addressDTO.getBuildingName() != null) {
                address.setBuildingName(addressDTO.getBuildingName());
            }
            if (addressDTO.getCity() != null) {
                address.setCity(addressDTO.getCity());
            }
            if (addressDTO.getPincode() != null) {
                address.setPincode(addressDTO.getPincode());
            }

//            // Save the updated student (which includes the updated address)
            studentRepo.save(student);
            return true;
        }
        return false;
        
        
//        Student.dbStudent=null;
//        Optional<Student> optionalStudent = studentRepo.findById(rollnumber);
//        if(!optionalStudent.isPresent())
//        	return null;
//        
//        dbStudent = optionalStudent.get();
//        Address dbAddress = dbStudent.getAddress();
//        dbStudent.setCity(address.getCity());
//        dbStudent.setAddress(dbAddress);
//        Student student = studentRepo.save(dbStudent);
//        return student.getAddress();
    }
}


