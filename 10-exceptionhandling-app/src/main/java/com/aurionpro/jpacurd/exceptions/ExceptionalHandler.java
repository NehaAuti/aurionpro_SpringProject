package com.aurionpro.jpacurd.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.aurionpro.jpacurd.errors.StudentErrorResponse;
@ControllerAdvice
public class ExceptionalHandler {

	 @ExceptionHandler
	    public ResponseEntity<StudentErrorResponse> handleStudentNotFoundException(StudentNotFoundException exception)
	    {
	    	StudentErrorResponse error = new StudentErrorResponse();
	    	error.setStatusCode(HttpStatus.NOT_FOUND.value());
	    	error.setErrorMessage(exception.getMessage());
	    	error.setTimestamp(System.currentTimeMillis());
	    	
	    	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	    
	    @ExceptionHandler
	    public ResponseEntity<StudentErrorResponse> handleStudentException(MethodArgumentTypeMismatchException exception)
	    {
	    	StudentErrorResponse error = new StudentErrorResponse();
	    	error.setStatusCode(HttpStatus.NOT_FOUND.value());
	    	error.setErrorMessage("Roll Number has to be Integer");
	    	error.setTimestamp(System.currentTimeMillis());
	    	
	    	return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	    
	    
	    
}
