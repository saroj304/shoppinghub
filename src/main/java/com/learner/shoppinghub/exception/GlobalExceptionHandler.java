package com.learner.shoppinghub.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<?> createUserNotFoundException(UsernameNotFoundException usernameNotFoundException) {

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage(usernameNotFoundException.getMessage());
        errorDetails.setStatus(HttpStatus.NOT_FOUND);
        errorDetails.setStatusCode(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
