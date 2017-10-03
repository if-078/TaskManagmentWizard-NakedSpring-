package com.softserve.academy.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class User {

  private int user_id;
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

  public void setId(int id) {
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

  @Override
  public int hashCode() {

    return HashCodeBuilder.reflectionHashCode(this, true);
  }

  @Override
  public boolean equals(Object obj) {

    return EqualsBuilder.reflectionEquals(this, obj, true);
  }

  @Override
  public String toString() {

    return ToStringBuilder.reflectionToString(this);
  }

}
