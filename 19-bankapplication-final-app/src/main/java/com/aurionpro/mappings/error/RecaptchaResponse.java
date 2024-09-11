package com.aurionpro.mappings.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RecaptchaResponse {
	 private boolean success;
	    private String challenge_ts;
	    private String hostname;
	    private String[] errorCodes;


}
