package com.aurionpro.mappings.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aurionpro.mappings.entity.Role;
import com.aurionpro.mappings.entity.User;
import com.aurionpro.mappings.service.RoleService;
import com.aurionpro.mappings.service.UserService;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/studentapp")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("users/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PutMapping("/roles/{userId}")
    public ResponseEntity<User> assignRolesToUser(
        @PathVariable Long userId,
        @RequestBody Set<Long> roleIds) {
        return ResponseEntity.ok(userService.updateUserRoles(userId, roleIds));
    }
}