package com.softserve.academy.tmw.validator;

import java.util.List;

public class ErrorResource {
    private String code;
    private List<FieldErrorResource> fieldErrors;

    public ErrorResource() { }

    public ErrorResource(String code) {
        this.code = code;
    }

    public String getCode() { return code; }

    public void setCode(String code) { this.code = code; }

    public List<FieldErrorResource> getFieldErrors() { return fieldErrors; }

    public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
