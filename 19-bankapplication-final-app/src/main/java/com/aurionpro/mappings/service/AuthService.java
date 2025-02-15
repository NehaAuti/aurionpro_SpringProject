package com.aurionpro.mappings.service;

import com.aurionpro.mappings.dto.CustomerDto;

import com.aurionpro.mappings.dto.JwtAuthResponse;
import com.aurionpro.mappings.dto.LoginDTO;
import com.aurionpro.mappings.dto.RegistrationDTO;
import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.User;

public interface AuthService {
	
	User register(RegistrationDTO registration);
	CustomerDto addNewCustomer(CustomerDto customerDTO) ;
	String login(LoginDTO loginDto) ;

}
