package com.aurionpro.jpacurd.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.aurionpro.jpacurd.errors.PaymentErrorResponse;



@ControllerAdvice
public class PaymentExceptionalHandler {
	
	 @ExceptionHandler
	    public ResponseEntity<PaymentErrorResponse> handleStudentNotFoundException(PaymentNotFoundException exception) {
	        PaymentErrorResponse error = new PaymentErrorResponse();
	        error.setStatusCode(HttpStatus.NOT_FOUND.value());
	        error.setErrorMessage(exception.getMessage());
	        error.setTimestamp(System.currentTimeMillis());
	        
	        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	    }
	    
	    @ExceptionHandler
	    public ResponseEntity<PaymentErrorResponse> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
	        PaymentErrorResponse error = new PaymentErrorResponse();
	        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
	        error.setErrorMessage("Invalid argument type: " + exception.getMessage());
	        error.setTimestamp(System.currentTimeMillis());
	        
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	    @ExceptionHandler(InvalidEnumValueException.class)
	    public ResponseEntity<PaymentErrorResponse> handleInvalidEnumValueException(InvalidEnumValueException exception) {
	        PaymentErrorResponse error = new PaymentErrorResponse();
	        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
	        error.setErrorMessage(exception.getMessage());
	        error.setTimestamp(System.currentTimeMillis());

	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

	    @ExceptionHandler
	    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
	        Map<String, String> errors = new HashMap<>();
	        
	        exception.getBindingResult().getFieldErrors().forEach(error -> {
	            errors.put(error.getField(), error.getDefaultMessage());
	        });
	        
	        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	    }
	}

