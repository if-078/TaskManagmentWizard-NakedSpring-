package com.softserve.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

@RestControllerAdvice
public class ErrorController {
 
  private static final String FALLBACKOPTION = "Error of validation";
  private static final int FIRST_FIELD_NOT_PASSED_VALIDATION = 0;
  
  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public String validationErrorHandler(MethodArgumentNotValidException ex) {
    FieldError error = ex.getBindingResult().getFieldErrors().get(FIRST_FIELD_NOT_PASSED_VALIDATION);
    
    return messageSource.getMessage(error.getCode() + "." + error.getObjectName() + "." + error.getField(), null, FALLBACKOPTION, null);
  }

}
