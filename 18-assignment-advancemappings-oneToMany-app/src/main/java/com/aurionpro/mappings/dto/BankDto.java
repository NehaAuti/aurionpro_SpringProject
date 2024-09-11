package com.aurionpro.mappings.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankDto {
	
    private int bankId;   
    private String bankName;
    private String ifscNo;


}
