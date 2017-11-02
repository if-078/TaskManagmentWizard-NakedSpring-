package com.softserve.academy.tmw.entity;

import java.sql.Time;
import java.util.Date;

public class Task implements NamedEntity {

    private int id;
    private String name;
    private Date createdDate;
    private Date startDate;
    private Date endDate;
    private Time estimateTime;
    private int assignTo;
    private int statusId;
    private int priorityId;
    private int parentId;

    public Task() {

    }

    public Task(String name, Date createdDate, Date startDate, Date endDate, Time estimateTime, int assignTo, int statusId, int priorityId, int parentId) {
        this.name = name;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimateTime = estimateTime;
        this.assignTo = assignTo;
        this.statusId = statusId;
        this.priorityId = priorityId;
        this.parentId = parentId;
    }

    public Task(int id, String name, Date createdDate, Date startDate, Date endDate, Time estimateTime, int assignTo, int statusId, int priorityId, int parentId) {
        this.id = id;
        this.name = name;
        this.createdDate = createdDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.estimateTime = estimateTime;
        this.assignTo = assignTo;
        this.statusId = statusId;
        this.priorityId = priorityId;
        this.parentId = parentId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setEstimateTime(Time estimateTime) {
        this.estimateTime = estimateTime;
    }

    public void setAssignTo(int assignTo) {
        this.assignTo = assignTo;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public void setPriorityId(int priorityId) {
        this.priorityId = priorityId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Time getEstimateTime() {
        return estimateTime;
    }

    public int getAssignTo() {
        return assignTo;
    }

    public int getStatusId() {
        return statusId;
    }

    public int getPriorityId() {
        return priorityId;
    }

    public int getParentId() {
        return parentId;
    }

    public String toString() {
        String taskStr = "";
        taskStr += this.getId() + " | " + this.getName() + " | " + this.getCreatedDate() + " | " + this.getStartDate() + " | " + this.getEndDate() + " | " + this.getEstimateTime() + " | " + this.getAssignTo() + " | " + this.getStatusId() + " | " + this.getPriorityId();
        return taskStr;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return id == task.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}