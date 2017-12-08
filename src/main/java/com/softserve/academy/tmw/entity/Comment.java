package com.softserve.academy.tmw.entity;

import java.util.Date;

public class Comment {

  private int id;
  private String commentText;
  private Date createdDate;
  private int taskId;
  private int userId;
  private String user;

  public Comment() {
  }

  public Comment(String commentText, int taskId, int userId) {
    this.commentText = commentText;
    this.taskId = taskId;
    this.userId = userId;

  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCommentText() {
    return commentText;
  }

  public void setCommentText(String commentText) {
    this.commentText = commentText;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public int getTaskId() {
    return taskId;
  }

  public void setTaskId(int taskId) {
    this.taskId = taskId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  @Override
  public String toString() {
    return "Comment{" +
        "id=" + id +
        ", commentText='" + commentText + '\'' +
        ", createdDate=" + createdDate +
        ", taskId=" + taskId +
        ", userId=" + userId +
        ", user='" + user + '\'' +
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

    Comment comment = (Comment) o;

    if (id != comment.id) {
      return false;
    }
    return commentText != null ? commentText.equals(comment.commentText)
        : comment.commentText == null;

  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (commentText != null ? commentText.hashCode() : 0);
    return result;
  }
}
