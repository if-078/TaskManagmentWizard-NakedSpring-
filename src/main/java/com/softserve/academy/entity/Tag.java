/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.softserve.academy.entity;

/**
 *
 * @author Oleg
 */
public class Tag {

  public int id;
  public String name;
  public int userId;

  public Tag(int id, String name, int userId) {
    this.id = id;
    this.name = name;
    this.userId = userId;
  }

  public Tag(String name, int userId) {
    this.name = name;
    this.userId = userId;
  }

  public Tag() {}

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getUserId() {
    return userId;
  }

  public void setId(int tagId) {
    this.id = tagId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  @Override
  public String toString() {
    return "Tag{" + "Id=" + id + ", name=" + name + ", userId=" + userId + '}';
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 89 * hash + this.id;
    hash = 89 * hash + this.userId;
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final Tag other = (Tag) obj;
    return this.id == other.id;
  }

}