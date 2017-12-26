package com.softserve.academy.tmw.entity;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;

public class Task {

  private int id;
  private String name;
  private Date createdDate;
  private Date planningDate;
  private Date startDate;
  private Date endDate;
  private int estimateTime;
  private int spentTime;
  private int leftTime;
  private int assignTo;
  private int statusId;
  private int priorityId;
  private int parentId;
  private int authorId;
  private int projectId;
  private String jiraKey;

  public String getJiraKey() {
    return jiraKey;
  }

  public void setJiraKey(String jiraKey) {
    this.jiraKey = jiraKey;
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

  private Status status;
  private Priority priority;
  private Tag tags[];
  private Comment comments[];

  public Task() {

  }

  public Task(String name, Date createdDate, Date planningDate, Date startDate, Date endDate,
      int estimateTime, int spentTime, int leftTime, int assignTo, int statusId, int priorityId,
      int parentId, int authorId, int projectId) {
    this.name = name;
    this.createdDate = createdDate;
    this.planningDate = planningDate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.estimateTime = estimateTime;
    this.spentTime = spentTime;
    this.leftTime = leftTime;
    this.assignTo = assignTo;
    this.statusId = statusId;
    this.priorityId = priorityId;
    this.parentId = parentId;
    this.authorId = authorId;
    this.projectId = projectId;
  }

  public Task(int id, String name, Date createdDate, Date planningDate, Date startDate,
      Date endDate, int estimateTime, int spentTime, int leftTime, int assignTo,
      int statusId, int priorityId, int parentId, int authorId, int projectId) {
    this.id = id;
    this.name = name;
    this.createdDate = createdDate;
    this.planningDate = planningDate;
    this.startDate = startDate;
    this.endDate = endDate;
    this.estimateTime = estimateTime;
    this.spentTime = spentTime;
    this.leftTime = leftTime;
    this.assignTo = assignTo;
    this.statusId = statusId;
    this.priorityId = priorityId;
    this.parentId = parentId;
    this.authorId = authorId;
    this.projectId = projectId;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public void setPlanningDate(Date planningDate) {
    this.planningDate = planningDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public void setEstimateTime(int estimateTime) {
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

  public String getName() {
    return name;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public Date getPlanningDate() {
    return planningDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public int getEstimateTime() {
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

  public Tag[] getTags() {
    return tags;
  }

  public void setTags(Tag[] tags) {
    this.tags = tags;
  }

  public Comment[] getComments() {
    return comments;
  }

  public void setComments(Comment[] comments) {
    this.comments = comments;
  }

  @Override
  public String toString() {
    return "Task{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", createdDate=" + createdDate +
            ", planningDate=" + planningDate +
            ", startDate=" + startDate +
            ", endDate=" + endDate +
            ", estimateTime=" + estimateTime +
            ", spentTime=" + spentTime +
            ", leftTime=" + leftTime +
            ", assignTo=" + assignTo +
            ", statusId=" + statusId +
            ", priorityId=" + priorityId +
            ", parentId=" + parentId +
            ", authorId=" + authorId +
            ", projectId=" + projectId +
            ", status=" + status +
            ", priority=" + priority +
            ", tags=" + Arrays.toString(tags) +
            ", comments=" + Arrays.toString(comments) +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Task task = (Task) o;

    return id == task.id;
  }

  @Override
  public int hashCode() {
    return id;
  }
}