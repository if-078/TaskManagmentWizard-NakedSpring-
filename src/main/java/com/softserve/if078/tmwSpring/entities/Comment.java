package com.softserve.if078.tmwSpring.entities;

import java.time.LocalDateTime;

public class Comment {
    private int commentId;
    private String commentText;
    private LocalDateTime createdDate = LocalDateTime.now();;
    private int taskId;
    private int userId;

    public Comment() {
    }

    public Comment(String commentText, int taskId, int userId) {
        this.commentText = commentText;
        this.taskId = taskId;
        this.userId = userId;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
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
}
