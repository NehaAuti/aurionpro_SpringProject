package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class JwtAuthResponse {
private String accessToken;
private String tokenType="Bearer";
}
