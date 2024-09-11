package com.aurionpro.jpacurd.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.aurionpro.jpacurd.errors.GenericError;
import com.aurionpro.jpacurd.errors.LoanErrorResponse;
import com.aurionpro.jpacurd.errors.LoanStatusError;
import com.aurionpro.jpacurd.errors.PaymentModeError;
import com.aurionpro.jpacurd.errors.PaymentStatusError;

@ControllerAdvice
public class LoanExceptionHandler {

	@ExceptionHandler
    public ResponseEntity<LoanErrorResponse> handleLoanNotFoundException(LoanNotFoundException exception) {
        LoanErrorResponse error = new LoanErrorResponse();
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setErrorMessage(exception.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<LoanErrorResponse> handleArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        LoanErrorResponse error = new LoanErrorResponse();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setErrorMessage("Invalid argument type: " + exception.getMessage());
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
    

    @ExceptionHandler
    public ResponseEntity<?> handlePaymentStatusException(HttpMessageNotReadableException exception){
    String message = exception.getMessage();
    
    if (message.contains("LoanStatus")) 
    {
        LoanStatusError error = new LoanStatusError();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setErrormessage("Loan status should be [ACTIVE,CLOSED,DEFAULTED,PENDING]");
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    } 
    
    else if (message.contains("PaymentStatus")) 
    {
        PaymentStatusError error = new PaymentStatusError();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setErrormessage("Payment status should be [PENDING, COMPLETED,FAILED,CANCELLED]");
        error.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        
    } 
    
    else if (message.contains("PaymentMode")) 
    {
        PaymentModeError error = new PaymentModeError();
        error.setStatusCode(HttpStatus.BAD_REQUEST.value());
        error.setErrormessage("Payment mode should be [CREDIT_CARD, DEBIT_CARD,NET_BANKING,UPI,CASH,CHEQUE]");
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

    
    @ControllerAdvice
    public class GlobalExceptionHandler {
        
        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
            StringBuilder errors = new StringBuilder();
            ex.getBindingResult().getAllErrors().forEach(error -> {
                errors.append(error.getDefaultMessage()).append(" ");
            });
            return ResponseEntity.badRequest().body(errors.toString());
        }}
}