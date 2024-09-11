package com.aurionpro.jpacurd.errors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
public class PaymentModeError {
		
			private int statusCode;
			private String errormessage;
			private long timestamp;
}
