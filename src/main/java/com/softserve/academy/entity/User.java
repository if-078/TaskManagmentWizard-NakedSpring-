package com.softserve.academy.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


public class User {

  private Integer user_id;
  private String name;
  private String pass;
  private String email;

  public User() {}

  public User(String name, String pass, String email) {
    this.name = name;
    this.pass = pass;
    this.email = email;
  }

  public int getId() {
    return user_id;
  }

  public void setId(Integer id) {
    this.user_id = id;
  }

  @NotBlank
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @NotBlank
  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }

  @NotBlank
  @Pattern(regexp = "^(.+)@(.+)$")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
}
