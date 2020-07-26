package no.torustad.medium.customer.config;

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
         String bodyOfResponse = "handleConflict()[CustomerControllerAdvice]: Unable to process request because of invalid input data";

         return handleExceptionInternal(re, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, wr);
     }

    @ExceptionHandler(value = {CustomerSystemException.class})
    protected ResponseEntity<Object> handleSystemException(RuntimeException re, WebRequest wr) {
        

        // Testen i CustomerControllerTest feilet fordi den forventet json, så derfor:
        String j = "{\"melding\": \"handleSystemException()[CustomerControllerAdvic]: Unable to process request because of system exception\"}";

        // String bodyOfResponse = String.format("handleSystemException()[CustomerControllerAdvic]: Unable to process request because of system exception");
        return handleExceptionInternal(re, j, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, wr);
    }
    
}