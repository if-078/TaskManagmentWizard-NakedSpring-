package com.softserve.academy.tmw.dto;

import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Tag;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class TaskDTO {

  private int id;
  private String name;
  private String createdDate;
  private String draftPlanning;
  private String planningDate;
  private int estimateTime;
  private int spentTime;
  private int leftTime;
  private int author;
  private int assignTo;
  private int statusId;
  private int priorityId;
  private int parentId;
  private int projectId;
  private Tag tags[];
  private Comment comments[];

  public int getId() {
    return id;
  }

  @NotBlank
  @Size(min = 1, max = 45)
  public String getName() {
    return name;
  }

  public String getCreatedDate() {
    return createdDate;
  }

  @NotBlank
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
  public String getDraftPlanning() {
    return draftPlanning;
  }

  public String getPlanningDate() {
    return planningDate;
  }

  public int getEstimateTime() {
    return estimateTime;
  }

  public int getSpentTime() {
    return spentTime;
  }

  public int getLeftTime() {
    return leftTime;
  }

  public int getAuthor() {
    return author;
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

  public int getProjectId() {
    return projectId;
  }

  public Tag[] getTags() {
    return tags;
  }

  public Comment[] getComments() {
    return comments;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCreatedDate(String createdDate) {
    this.createdDate = createdDate;
  }

  public void setDraftPlanning(String draftPlanning) {
    this.draftPlanning = draftPlanning;
  }

  public void setPlanningDate(String planningDate) {
    this.planningDate = planningDate;
  }

  public void setEstimateTime(int estimateTime) {
    this.estimateTime = estimateTime;
  }

  public void setSpentTime(int spentTime) {
    this.spentTime = spentTime;
  }

  public void setLeftTime(int leftTime) {
    this.leftTime = leftTime;
  }

  public void setAuthor(int author) {
    this.author = author;
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

  public void setProjectId(int projectId) {
    this.projectId = projectId;
  }

  public void setTags(Tag[] tags) {
    this.tags = tags;
  }

  public void setComments(Comment[] comments) {
    this.comments = comments;
  }
}
