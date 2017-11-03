package com.softserve.academy.tmw.controller;

import com.softserve.academy.tmw.validator.ErrorResource;
import com.softserve.academy.tmw.validator.FieldErrorResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorController {
 
  private static final String FALLBACKOPTION = "Error of validation";
  
  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResource validationErrorHandler(MethodArgumentNotValidException ex) {
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

    for (FieldError error:fieldErrors){
        System.out.println(error.getField().replaceAll("(.)([A-Z])", "$1.$2"));
    }
      /*List<FieldErrorResource> fieldErrorResources = fieldErrors.stream()
            .map(error -> new FieldErrorResource(
                    error.getObjectName(),
                    error.getField(),
                    messageSource.getMessage(error.getCode() + "." + error.getObjectName() + "." + error.getField().replaceAll("(.)([A-Z])", "$1.$2"), null, FALLBACKOPTION, null)))
            .collect(Collectors.toList());

    ErrorResource errorResource = new ErrorResource("ErrorValid");
    errorResource.setFieldErrors(fieldErrorResources);*/

    //return errorResource;

      return new ErrorResource();
  }

  @ExceptionHandler(EmptyResultDataAccessException.class)
  public String emptyResultDataAccessHandler(EmptyResultDataAccessException ex) {
    return null;
  }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String error500Default(Exception e) {
        return "500";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String error404Default(NoHandlerFoundException ex) {
        return "404";
    }
}
