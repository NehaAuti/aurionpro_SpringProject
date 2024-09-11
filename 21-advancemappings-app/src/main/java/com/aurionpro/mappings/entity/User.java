package com.aurionpro.mappings.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;
    
    @Column(name = "userName")
    private String userName;
    
    @Column(name = "password")
    private String password;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "userId"),
        inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    private Set<Role> roles;
}