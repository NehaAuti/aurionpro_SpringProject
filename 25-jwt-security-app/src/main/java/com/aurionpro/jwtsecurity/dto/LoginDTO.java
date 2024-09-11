package com.aurionpro.jwtsecurity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class LoginDTO {
private String username;
private String password;

}
