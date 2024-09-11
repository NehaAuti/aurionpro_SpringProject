package com.aurionpro.mappings.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.repository.RoleRepository;
import com.aurionpro.mappings.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElse(null);
    }
    
    @Override
    public Role updateRoleUsers(Long roleId, Set<Long> userIds) {
        Role role = getRoleById(roleId);
        
        // Fetch users from the database using userIds
        Set<User> users = userIds.stream()
                .map(id -> userRepository.findById(id)
                                         .orElseThrow(() -> new EntityNotFoundException("User not found with ID " + id)))
                .collect(Collectors.toSet());

        
        return roleRepository.save(role);
    }



}
