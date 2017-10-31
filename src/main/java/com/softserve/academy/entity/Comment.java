package com.softserve.academy.entity;

import java.time.LocalDateTime;

public class Comment {
    private int id;
    private String commentText;
    private LocalDateTime createdDate = LocalDateTime.now();
    private int taskId;
    private int userId;

    public Comment() {
    }

    public Comment(String commentText, int taskId, int userId) {
        this.commentText = commentText;
        this.taskId = taskId;
        this.userId = userId;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getCommentText() {return commentText;}

    public void setCommentText(String commentText) {this.commentText = commentText;}

    public LocalDateTime getCreatedDate() {return createdDate;}

    public void setCreatedDate(LocalDateTime createdDate) {this.createdDate = createdDate;}

    public int getTaskId() {return taskId;}

    public void setTaskId(int taskId) {this.taskId = taskId;}

    public int getUserId() {return userId;}

    public void setUserId(int userId) {this.userId = userId;}
}
