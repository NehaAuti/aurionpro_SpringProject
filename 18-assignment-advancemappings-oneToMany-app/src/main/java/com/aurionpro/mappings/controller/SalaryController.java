package com.aurionpro.mappings.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.entity.Salary;
import com.aurionpro.mappings.service.SalaryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/bankapp")
public class SalaryController {

    @Autowired
    private SalaryService salaryService;

    @PostMapping("/salary")
    public ResponseEntity<Salary> addSalary(@Valid @RequestBody Salary salary) {
        Salary savedSalary = salaryService.addSalary(salary);
        return new ResponseEntity<>(savedSalary, HttpStatus.CREATED);
    }
    @GetMapping("salary/{salaryId}")
    public ResponseEntity<Salary> getSalaryById(@PathVariable int salaryId) {
        Salary salary = salaryService.getSalaryById(salaryId);
        if (salary != null) {
            return ResponseEntity.ok(salary);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/salary")
    public ResponseEntity<List<Salary>> getAllSalaries() {
        List<Salary> salaries = salaryService.getAllSalaries();
        return ResponseEntity.ok(salaries);
    }

    @PutMapping("salary/{salaryId}")
    public ResponseEntity<Salary> updateSalary(@PathVariable int salaryId, @RequestBody Salary salary) {
        salary.setSalaryId(salaryId);
        Salary updatedSalary = salaryService.updateSalary(salary);
        if (updatedSalary != null) {
            return ResponseEntity.ok(updatedSalary);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("salary/{salaryId}")
    public ResponseEntity<Void> deleteSalary(@PathVariable int salaryId) {
        salaryService.deleteSalary(salaryId);
        return ResponseEntity.noContent().build();
    }
}