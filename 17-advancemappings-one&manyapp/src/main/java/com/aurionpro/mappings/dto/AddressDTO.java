package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddressDTO {
    private String buildingName;
    private String city;
    private Integer pincode; // Use Integer to allow null values for partial updates
}