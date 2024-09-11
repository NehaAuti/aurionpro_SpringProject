package com.aurionpro.jwtsecurity.services;

import com.aurionpro.jwtsecurity.dto.LoginDTO;
import com.aurionpro.jwtsecurity.dto.RegistrationDTO;
import com.aurionpro.jwtsecurity.entity.User;

public interface AuthService {
	
	User register(RegistrationDTO registration);
	String login(LoginDTO loginDto);

}
