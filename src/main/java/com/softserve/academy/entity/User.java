package com.softserve.academy.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.softserve.academy.validator.UniqUserEmail;

import java.util.Objects;


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
  @UniqUserEmail
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
  
  @Override
  public boolean equals(Object obj) 
  {
     if (obj == null)
     {
        return false;
     }
     if (getClass() != obj.getClass())
     {
        return false;
     }
     final User other = (User) obj;
     return    Objects.equals(this.user_id, other.user_id)
            && Objects.equals(this.name, other.name)
            && Objects.equals(this.email, other.email)
            && Objects.equals(this.pass, other.pass);
  }

}
