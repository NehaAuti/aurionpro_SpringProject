package com.aurionpro.mappings.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.repository.RoleRepository;
import com.aurionpro.mappings.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }


	 @Override
	    public User updateUserRoles(Long userId, Set<Long> roleIds) {
	        User user = getUserById(userId);
	        Set<Role> roles = new HashSet<>();
	        for (Long roleId : roleIds) {
	            Role role = roleRepository.findById(roleId)
	                .orElseThrow(() -> new RuntimeException("Role not found"));
	            roles.add(role);
	        }
	        user.setRoles(roles);
	        return userRepository.save(user);
	    }
}