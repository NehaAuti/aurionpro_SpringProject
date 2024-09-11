package com.aurionpro.di.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aurionpro.di.entity.Computer;
import com.aurionpro.di.entity.Harddisk;

@RestController
public class ComputerController {

	@Autowired
	private Computer computer;
	
	@GetMapping("/computers")
	public Computer getComputers()
	{
		return computer;
	}
	@Autowired
	private Harddisk harddisk;
	
	@GetMapping("/harddisks")
	public Harddisk harddisk()
	{
		return harddisk;
	}
}
