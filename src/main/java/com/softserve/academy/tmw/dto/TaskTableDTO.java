package com.softserve.academy.tmw.dto;

import java.sql.Time;
import java.util.Date;

public class TaskTableDTO {

  private int id;
  private String name;
  private Date startDate;
  private String estimateTime;
  private String assignTo;
  private String status;
  private String priority;


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

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getEstimateTime() {
    return estimateTime;
  }

  public void setEstimateTime(int estimateTime) {
    int hours = estimateTime/60;
    String minuts = String.valueOf(estimateTime%60);
    minuts = minuts.length() < 2 ? "0" + minuts : minuts;
    this.estimateTime = hours + ":" + minuts + ":00";
  }

  public String getAssignTo() {
    return assignTo;
  }

  public void setAssignTo(String assignTo) {
    this.assignTo = assignTo;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }
}