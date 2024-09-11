package com.aurionpro.mappings.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "admin")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminId")
    private int adminId;

    @NotBlank(message = "Username is mandatory")
    @Email(message = "Username should be a valid email")
    @Column(name = "username", unique = true)
    private String username;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$", 
             message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    @Column(name = "password")
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(name="role")
    private UserRole role;
}