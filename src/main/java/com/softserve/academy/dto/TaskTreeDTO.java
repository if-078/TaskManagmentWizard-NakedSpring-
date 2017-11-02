package com.softserve.academy.dto;

public class TaskTreeDTO {
    private int id;
    private String text;
    private boolean isChildren;

    public TaskTreeDTO() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isChildren() {
        return isChildren;
    }

    public void setChildren(boolean children) {
        isChildren = children;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
