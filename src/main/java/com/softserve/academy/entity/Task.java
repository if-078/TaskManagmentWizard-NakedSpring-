package com.softserve.academy.entity;

import java.sql.Time;
import java.util.Date;

public class Task {

    private int id;
    private String name;
    private Date created_date;
    private Date start_date;
    private Date end_date;
    private Time estimate_time;
    private int assign_to;
    private int status_id;
    private int priority_id;
    private int parent_id;

    public Task() {

    }

    public Task(String name, Date created_date, Date start_date, Date end_date, Time estimate_time, int assign_to, int status_id, int priority_id, int parent_id) {
        //this.id = id;
        this.name = name;
        this.created_date = created_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.estimate_time = estimate_time;
        this.assign_to = assign_to;
        this.status_id = status_id;
        this.priority_id = priority_id;
        this.parent_id = parent_id;
    }

    public Task(int id, String name, Date created_date, Date start_date, Date end_date, Time estimate_time, int assign_to, int status_id, int priority_id, int parent_id) {
        this.id = id;
        this.name = name;
        this.created_date = created_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.estimate_time = estimate_time;
        this.assign_to = assign_to;
        this.status_id = status_id;
        this.priority_id = priority_id;
        this.parent_id = parent_id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public void setEstimate_time(Time estimate_time) {
        this.estimate_time = estimate_time;
    }

    public void setAssign_to(int assign_to) {
        this.assign_to = assign_to;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public void setPriority_id(int priority_id) {
        this.priority_id = priority_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public Date getStart_date() {
        return start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public Time getEstimate_time() {
        return estimate_time;
    }

    public int getAssign_to() {
        return assign_to;
    }

    public int getStatus_id() {
        return status_id;
    }

    public int getPriority_id() {
        return priority_id;
    }

    public int getParent_id() {
        return parent_id;
    }

    public String toString() {
        String taskStr = "";
        taskStr += this.getId() + " | " + this.getName() + " | " + this.getCreated_date() + " | " + this.getStart_date() + " | " + this.getEnd_date() + " | " + this.getEstimate_time() + " | " + this.getAssign_to() + " | " + this.getStatus_id() + " | " + this.getPriority_id();
        return taskStr;
    }
}