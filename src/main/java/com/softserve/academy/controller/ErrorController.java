package com.softserve.academy.controller;

import com.softserve.academy.validator.ErrorResource;
import com.softserve.academy.validator.FieldErrorResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorController {
 
  private static final String FALLBACKOPTION = "Error of validation";
  
  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResource validationErrorHandler(MethodArgumentNotValidException ex) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

    List<FieldErrorResource> fieldErrorResources = fieldErrors.stream()
            .map(error -> new FieldErrorResource(
                    error.getObjectName(),
                    error.getField(),
                    messageSource.getMessage(error.getCode() + "." + error.getObjectName() + "." + error.getField(), null, FALLBACKOPTION, null)))
            .collect(Collectors.toList());

    ErrorResource errorResource = new ErrorResource("ErrorValid");
    errorResource.setFieldErrors(fieldErrorResources);

    return errorResource;
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public String emptyResultDataAccessHandler(EmptyResultDataAccessException ex) {
    System.out.println(ex.toString());

    return null;
  }

}
