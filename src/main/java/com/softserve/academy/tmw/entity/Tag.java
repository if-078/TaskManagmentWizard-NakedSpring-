package com.softserve.academy.tmw.entity;

public class Tag {

  private int id;
  private String name;
  private int userId;
  private int projectId;

  public Tag(int id, String name, int userId, int projectId) {
    this.id = id;
    this.name = name;
    this.userId = userId;
    this.projectId = projectId;
  }

  public Tag() {

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

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getProjectId() {
    return projectId;
  }

  public void setProjectId(int projectId) {
    this.projectId = projectId;
  }

  @Override
  public String toString() {
    return "Tag{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", userId=" + userId +
        ", projectId=" + projectId +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Tag tag = (Tag) o;

    return id == tag.id;

  }

  @Override
  public int hashCode() {
    return id;
  }
}