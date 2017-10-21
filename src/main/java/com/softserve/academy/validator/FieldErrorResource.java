package com.softserve.academy.validator;

public class FieldErrorResource {
    private String resource;
    private String field;
    private String message;

    public FieldErrorResource(String resource, String field, String message){
        this.resource = resource;
        this.field = field;
        this.message = message;
    }
    public FieldErrorResource(){}

    public String getResource() { return resource; }

    public void setResource(String resource) { this.resource = resource; }

    public String getField() { return field; }

    public void setField(String field) { this.field = field; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }
}
