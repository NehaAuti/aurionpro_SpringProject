package com.aurionpro.mappings.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleId")
    private Long roleId;
    
    @Column(name = "roleName")
    private String roleName;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "userId"),
        inverseJoinColumns = @JoinColumn(name = "roleId")
    )
    //@ManyToMany(mappedBy = "roles")
    private Set<User> users;
}