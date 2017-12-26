package com.softserve.academy.tmw.entity;

public class Status {

  private int id;
  private String name;

  public Status() {
  }

  public Status(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 17 * hash + this.id;
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
    final Status other = (Status) obj;
    return this.id == other.id;
  }

  @Override
  public String toString() {
    return "Status{" + "id=" + id + ", name=" + name + '}';
  }


}