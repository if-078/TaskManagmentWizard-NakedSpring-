package com.softserve.academy.tmw.entity;


import java.util.Date;

public class SpentTime {

    private int id;
    private int userId;
    private int taskId;
    private Date date;
    private int logTime;
    private String userName;

    public SpentTime(int id, int userId, int taskId, Date date, int logTime, String userName, String taskName) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.date = date;
        this.logTime = logTime;
        this.userName = userName;
        this.taskName = taskName;
    }

    private String taskName;

    public SpentTime(int userId, int taskId, Date date, int logTime) {
        this.userId = userId;
        this.taskId = taskId;
        this.date = date;
        this.logTime = logTime;
    }

    public SpentTime(int id, int userId, int taskId, Date date, int logTime) {
        this.id = id;
        this.userId = userId;
        this.taskId = taskId;
        this.date = date;
        this.logTime = logTime;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLogTime() {
        return logTime;
    }

    public void setLogTime(int logTime) {
        this.logTime = logTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
}
