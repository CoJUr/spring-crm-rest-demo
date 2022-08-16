package com.luv2code.springdemo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//global exception handler
@ControllerAdvice
public class CustomerRestExceptionHandler {

    //add handler for CustomerNotFoundException
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(CustomerNotFoundException exc) {

        //create CustomerErrorResponse, then return ResponseEntity
        CustomerErrorResponse error = new CustomerErrorResponse(
                HttpStatus.NOT_FOUND.value(), exc.getMessage(),
                System.currentTimeMillis());

//        return ResponseEntity.status(404).body(error);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    //handler for generic 400 Exception (all other exceptions)
    @ExceptionHandler
    public ResponseEntity<CustomerErrorResponse> handleException(Exception exc) {

        //create CustomerErrorResponse, then return ResponseEntity
        CustomerErrorResponse error = new CustomerErrorResponse(
                HttpStatus.BAD_REQUEST.value(), exc.getMessage(),
                System.currentTimeMillis());

//        return ResponseEntity.status(400).body(error);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
