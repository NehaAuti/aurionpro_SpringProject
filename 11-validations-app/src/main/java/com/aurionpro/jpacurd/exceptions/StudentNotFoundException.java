package com.aurionpro.jpacurd.exceptions;

public class StudentNotFoundException extends RuntimeException {

	public String getMessage()
	{
		return"Student you are searching for is not present";	}
}
