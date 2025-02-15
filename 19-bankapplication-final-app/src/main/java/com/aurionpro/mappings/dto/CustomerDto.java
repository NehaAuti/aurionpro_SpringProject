package com.aurionpro.mappings.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
	private int customerId;
	@NotBlank(message = "First name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "First name must contain only letters")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Last name must contain only letters")
    @Column(name = "last_name")
    private String lastName;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String emailId;
    
    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).*$", 
             message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character")
    private String password;  
    
    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    @Column(name = "mobile_number")
    private String mobileNumber;
    
   // @NotBlank(message = "dateofBirth is mandatory")
    private LocalDate dateOfBirth; 
}
