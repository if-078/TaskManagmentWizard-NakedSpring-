package com.softserve.academy.tmw.entity;

public class UsersTasks {
    private int id;
    private int userId;
    private int taskId;

    public UsersTasks(int userId, int taskId){
        this.userId = userId;
        this.taskId = taskId;
    }

    public UsersTasks(int id,int userId, int taskId){
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
