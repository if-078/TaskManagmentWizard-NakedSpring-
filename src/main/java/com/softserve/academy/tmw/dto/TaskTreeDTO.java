package com.softserve.academy.tmw.dto;

public class TaskTreeDTO {

  private int id;
  private String text;
  private boolean children;

  public TaskTreeDTO() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public boolean isChildren() {
    return children;
  }

  public void setChildren(boolean children) {
    this.children = children;
  }
}
