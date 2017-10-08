package com.softserve.academy.entity;

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


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPass() {
    return pass;
  }

  public void setPass(String pass) {
    this.pass = pass;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
