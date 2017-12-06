package com.softserve.academy.tmw.dto;

import java.sql.Time;
import java.util.Date;

public class TaskJiraDTO {
    private String name;
    /*private Date createdDate;
    private Date endDate;
    private Time estimateTime;
    private int assignTo;*/
    private int statusId;
    private int priorityId;
    private String jiraKey;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public String getJiraKey() {
        return jiraKey;
    }

    public void setJiraKey(String jiraKey) {
        this.jiraKey = jiraKey;
    }
}
