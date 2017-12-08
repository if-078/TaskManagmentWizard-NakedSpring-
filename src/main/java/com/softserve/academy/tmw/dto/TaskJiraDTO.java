package com.softserve.academy.tmw.dto;

import java.sql.Time;
import java.util.Date;

public class TaskJiraDTO {
    private String name;
    private String createdDate;
    private int assignTo;
    private int statusId;
    private int priorityId;
    private String jiraKey;
    private int parentId;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public int getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(int assignTo) {
        this.assignTo = assignTo;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public String getJiraKey() {
        return jiraKey;
    }
}
