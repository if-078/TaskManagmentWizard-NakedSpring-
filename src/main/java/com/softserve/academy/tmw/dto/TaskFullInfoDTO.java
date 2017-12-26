package com.softserve.academy.tmw.dto;

import com.softserve.academy.tmw.entity.Priority;
import com.softserve.academy.tmw.entity.Status;
import com.softserve.academy.tmw.entity.User;

import java.util.Date;

public class TaskFullInfoDTO {

  private int id;
  private String name;
  private Date createdDate;
  private Date planningDate;
  private Date draftPlanning;
  private String estimateTime;
  private String spentTime;
  private String leftTime;
  private User assignTo;
  private Status status;
  private Priority priority;
  private String author;
  private int parentId;
  private int projectId;

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

  public Date getDraftPlanning() {
    return draftPlanning;
  }

  public void setDraftPlanning(Date draftPlanning) {
    this.draftPlanning = draftPlanning;
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

  public String getSpentTime() {
    return spentTime;
  }

  public void setSpentTime(int spentTime) {
    int hours = spentTime/60;
    String minuts = String.valueOf(spentTime%60);
    minuts = minuts.length() < 2 ? "0" + minuts : minuts;
    this.spentTime = hours + ":" + minuts + ":00";  }

  public String getLeftTime() {
    return leftTime;
  }

  public void setLeftTime(int leftTime) {
    int hours = leftTime/60;
    String minuts = String.valueOf(leftTime%60);
    minuts = minuts.length() < 2 ? "0" + minuts : minuts;
    this.leftTime = hours + ":" + minuts + ":00";  }

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

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public int getParentId() {
    return parentId;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public int getProjectId() {
    return projectId;
  }

  public void setProjectId(int projectId) {
    this.projectId = projectId;
  }

  @Override
  public String toString() {
    return "TaskFullInfoDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", planningDate=" + planningDate +
            ", draftPlanning=" + draftPlanning +
            ", estimateTime='" + estimateTime + '\'' +
            ", spentTime='" + spentTime + '\'' +
            ", leftTime='" + leftTime + '\'' +
            ", assignTo=" + assignTo +
            ", status=" + status +
            ", priority=" + priority +
            ", author='" + author + '\'' +
            '}';
  }
}
