package no.torustad.medium.customer.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import no.torustad.medium.customer.CustomerSystemException;

@ControllerAdvice
public class CustomerControllerAdvice extends ResponseEntityExceptionHandler
 {
     @ExceptionHandler(value = {IllegalArgumentException.class})
     protected ResponseEntity<Object> handleConflict(RuntimeException re, WebRequest wr) {
         String bodyOfResponse = "Unable to process request because of invalid input data";

         return handleExceptionInternal(re, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, wr);
     }

    @ExceptionHandler(value = {CustomerSystemException.class})
    protected ResponseEntity<Object> handleSystemException(RuntimeException re, WebRequest wr) {
        
        String bodyOfResponse = String.format("Unable to process request because of system exception");
        return handleExceptionInternal(re, bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, wr);
    }
    
}