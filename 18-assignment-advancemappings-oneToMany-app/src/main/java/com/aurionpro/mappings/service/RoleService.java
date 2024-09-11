package com.aurionpro.mappings.service;

import java.util.Set;

import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleById(Long roleId);
    Role updateRoleUsers(Long roleId, Set<Long> userIds);
}