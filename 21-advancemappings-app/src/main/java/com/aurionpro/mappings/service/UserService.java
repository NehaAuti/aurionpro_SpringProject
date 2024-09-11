package com.aurionpro.mappings.service;

import java.util.Set;

import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;

public interface UserService {
	User createUser(User user);
    User getUserById(Long userId);
    User updateUserRoles(Long userId, Set<Long> roleIds);
}