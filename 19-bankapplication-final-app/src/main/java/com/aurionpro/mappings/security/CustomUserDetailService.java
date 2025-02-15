package com.aurionpro.mappings.security;

import java.util.Optional;
import java.util.Set;


import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.entity.Customer;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.repository.CustomerRepository;
import com.aurionpro.mappings.repository.UserRepository;



@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        // Fetch roles and map them to GrantedAuthority
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                .collect(Collectors.toSet());

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                authorities);
    }
}

    
    
    

