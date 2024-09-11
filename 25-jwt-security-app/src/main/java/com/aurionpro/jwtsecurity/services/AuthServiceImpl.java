package com.aurionpro.jwtsecurity.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aurionpro.jwtsecurity.dto.LoginDTO;
import com.aurionpro.jwtsecurity.dto.RegistrationDTO;
import com.aurionpro.jwtsecurity.entity.Role;
import com.aurionpro.jwtsecurity.entity.User;
import com.aurionpro.jwtsecurity.exception.UserApiException;
import com.aurionpro.jwtsecurity.respository.RoleRepository;
import com.aurionpro.jwtsecurity.respository.UserRepository;
import com.aurionpro.jwtsecurity.security.JwtTokenProvider;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

	@Autowired
    private final AuthenticationManager authenticationManager;
	@Autowired
    private final UserRepository userRepo;
	@Autowired
    private final RoleRepository roleRepo;
	@Autowired
    private final PasswordEncoder passwordEncoder;
	@Autowired
    private final JwtTokenProvider tokenProvider;

    @Override
    public User register(RegistrationDTO registration) {
        if (userRepo.existsByUsername(registration.getUsername())) {
            throw new UserApiException(HttpStatus.BAD_REQUEST, "User already exists");
        }

        User user = new User();
        user.setUsername(registration.getUsername());
        user.setPassword(passwordEncoder.encode(registration.getPassword()));

        List<Role> roles = new ArrayList<>();
        Role userRole = roleRepo.findByRolename(registration.getRole())
                .orElseThrow(() -> new UserApiException(HttpStatus.NOT_FOUND, "Role not found"));
        roles.add(userRole);
        user.setRoles(roles);

        return userRepo.save(user);
    }

    @Override
    public String login(LoginDTO loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return tokenProvider.generateToken(authentication);
        } catch (BadCredentialsException e) {
            throw new UserApiException(HttpStatus.NOT_FOUND, "Username or password is incorrect");
        }
    }
}
