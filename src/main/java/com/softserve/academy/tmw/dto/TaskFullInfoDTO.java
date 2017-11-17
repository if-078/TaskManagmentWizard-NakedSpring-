package com.softserve.academy.tmw.dto;

import com.softserve.academy.tmw.entity.Priority;
import com.softserve.academy.tmw.entity.Status;
import com.softserve.academy.tmw.entity.User;
import java.sql.Time;
import java.util.Date;

public class TaskFullInfoDTO {

  private int id;
  private String name;
  private Date createdDate;
  private Date startDate;
  private Date endDate;
  private Time estimateTime;
  private User assignTo;
  private Status status;
  private Priority priority;


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

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Time getEstimateTime() {
    return estimateTime;
  }

  public void setEstimateTime(Time estimateTime) {
    this.estimateTime = estimateTime;
  }

  public User getAssignTo() {
    return assignTo;
  }

  public void setAssignTo(User assignTo) {
    this.assignTo = assignTo;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }
}
