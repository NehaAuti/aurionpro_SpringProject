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
        // First, try to load the user as an admin
        Optional<User> userOpt = userRepo.findByUsername(username);
        if (userOpt.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                userOpt.get().getUsername(), 
                userOpt.get().getPassword(), 
                userOpt.get().getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                    .collect(Collectors.toList())
            );
        }

        // If not found, try to load as a customer
        Optional<Customer> customerOpt = customerRepository.findByEmailId(username);
        if (customerOpt.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                customerOpt.get().getEmailId(), 
                customerOpt.get().getPassword(), 
                customerOpt.get().getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRolename()))
                    .collect(Collectors.toList())
            );
        }

        // If neither found, throw an exception
        throw new UsernameNotFoundException("User not found with email or username: " + username);
    }
    }
	
//	private final UserRepository userRepo;
//    private final CustomerRepository customerRepository;
//
//    // Constructor for dependency injection
//    @Autowired
//    public CustomUserDetailService(UserRepository userRepo, CustomerRepository customerRepository) {
//        this.userRepo = userRepo;
//        this.customerRepository = customerRepository;
//    }
//    
//    
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        // First, try to load the user as an admin
//    	User user = userRepo.findByUsername(username).orElseThrow(
//				()-> new UsernameNotFoundException("User not found"));
//		
//		Set<GrantedAuthority> authorities = user
//				.getRoles()
//				.stream()
//				.map((role)->new SimpleGrantedAuthority(role.getRolename()))
//				.collect(Collectors.toSet());
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
//    }
    
    
 

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User user = userRepo.findByUsername(username)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//
//        Set<GrantedAuthority> authorities = user.getRoles().stream()
//                .map(role -> new SimpleGrantedAuthority(role.getRolename()))
//                .collect(Collectors.toSet());
//
//        return new org.springframework.security.core.userdetails.User(
//                user.getUsername(),
//                user.getPassword(),
//                authorities);
//    }
    

    

