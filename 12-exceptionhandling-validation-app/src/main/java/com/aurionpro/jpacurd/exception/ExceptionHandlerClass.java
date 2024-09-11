package com.aurionpro.jpacurd.exception;

import java.util.HashMap;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.aurionpro.jpacurd.errors.GenericError;
import com.aurionpro.jpacurd.errors.LoanStatusError;
import com.aurionpro.jpacurd.errors.PaymentModeError;
import com.aurionpro.jpacurd.errors.PaymentStatusError;


@ControllerAdvice
public class ExceptionHandlerClass {
	
    @ExceptionHandler
    public ResponseEntity<?> handleStudentException(MethodArgumentNotValidException exception) {
    	
    	Map<String,String> errors=new HashMap<>();
    	
    	exception.getBindingResult().getFieldErrors().forEach(error->{
    				errors.put(error.getField(), error.getDefaultMessage());
    			});
    	return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST); 
    	
    }
    
    
    
    @ExceptionHandler
    public ResponseEntity<?> handlePaymentStatusException(HttpMessageNotReadableException exception){
    String message = exception.getMessage();
    
    if (message.contains("LoanStatus")) 
    {
        LoanStatusError error = new LoanStatusError();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setErrormessage("Loan status should be [REJECTED, PENDING, APPROVED]");
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } 
    
    else if (message.contains("PaymentStatus")) 
    {
        PaymentStatusError error = new PaymentStatusError();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setErrormessage("Payment status should be [SUCCESSFUL, FAILED]");
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        
    } 
    
    else if (message.contains("PaymentMode")) 
    {
        PaymentModeError error = new PaymentModeError();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setErrormessage("Payment mode should be [UPI, CASH]");
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } 
    
    else {
        GenericError error = new GenericError();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setErrormessage("Invalid input");
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
}
