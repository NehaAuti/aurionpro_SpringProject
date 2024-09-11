package com.aurionpro.jpacurd.entity.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.jpacurd.entity.Employee;
import com.aurionpro.jpacurd.entity.service.EmployeeService;



@RestController
@RequestMapping("/employeeapp")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/employee")
	public ResponseEntity<Page<Employee>> getAllEmployee(@RequestParam int pageno, @RequestParam int pagesize)
	{
		return ResponseEntity.ok(employeeService.getAllEmployee(pageno,pagesize));
	}
	
    @GetMapping("employee/{employeeId}")
    public ResponseEntity<Optional<Employee>>  getByEmployeeId(@PathVariable int employeeId) 
    {	
    	return ResponseEntity.ok(employeeService. getByEmployeeId(employeeId));
    }

    @PostMapping("/employee")
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee) 
    {
        employeeService.addEmployee(employee);
        return ResponseEntity.ok("employee added successfully");
    }

    @DeleteMapping("employee/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int employeeId) 
    {

    	 employeeService.deleteEmployee((int) employeeId);
         return ResponseEntity.ok("Employee deleted sucessfully");
    }
    
    @GetMapping("/employees")
    public ResponseEntity<Page<Employee>> getAllEmployeesByName(
        @RequestParam(required = false) String firstName,
        @RequestParam int pageno,
        @RequestParam int pagesize) {
        
        if (firstName != null) {
            return ResponseEntity.ok(employeeService.getAllEmployeesByName(firstName, pageno, pagesize));
        }
        return ResponseEntity.ok(employeeService.getAllEmployee(pageno, pagesize));
    }

    

}
