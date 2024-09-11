package com.aurionpro.mappings.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.service.RoleService;

@RestController
@RequestMapping("/bankapp")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @GetMapping("role/{roleId}")
    public Role getRoleById(@PathVariable Long roleId) {
        return roleService.getRoleById(roleId);
    }
    
    @PostMapping("/users/{roleId}")
    public ResponseEntity<?> allocateRoleUsers(@PathVariable Long roleId, @RequestBody Set<Long> userIds) {
        // Directly pass the userIds set to the service layer
        return ResponseEntity.ok(roleService.updateRoleUsers(roleId, userIds));
    }



}