package com.blogapi5.exception;

import com.blogapi5.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ErrorDetails> handleResponseNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){
ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
  ResponseEntity<ErrorDetails> handleGlobalException(Exception exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(),webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
