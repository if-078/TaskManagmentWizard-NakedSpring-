package com.softserve.academy.tmw.dto;

import java.util.Date;

public class TaskTableDTO {

  private int id;             // 0
  private String name;        // 1
  private Date createdDate;   // 2
  private Date planningDate;  // 3
  private Date startDate;     // 4
  private Date endDate;       // 5
  private int estimateTime;   // 6
  private int spentTime;      // 7
  private int leftTime;       // 8
  private int assignTo;       // 9
  private int statusId;       // 10
  private int priorityId;     // 11
  private int parentId;       // 12
  private int authorId;       // 13
  private int projectId;      // 14
  private String assign;      // 15
  private String status;      // 16
  private String priority;    // 17

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

  public Date getPlanningDate() {
    return planningDate;
  }

  public void setPlanningDate(Date planningDate) {
    this.planningDate = planningDate;
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

  public int getEstimateTime() {
    return estimateTime;
  }

  public void setEstimateTime(int estimateTime) {
    this.estimateTime = estimateTime;
  }

  public int getSpentTime() {
    return spentTime;
  }

  public void setSpentTime(int spentTime) {
    this.spentTime = spentTime;
  }

  public int getLeftTime() {
    return leftTime;
  }

  public void setLeftTime(int leftTime) {
    this.leftTime = leftTime;
  }

  public int getAssignTo() {
    return assignTo;
  }

  public void setAssignTo(int assignTo) {
    this.assignTo = assignTo;
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

  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }

  public int getProjectId() {
    return projectId;
  }

  public void setProjectId(int projectId) {
    this.projectId = projectId;
  }

  public String getAssign() {
    return assign;
  }

  public void setAssign(String assign) {
    this.assign = assign;
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