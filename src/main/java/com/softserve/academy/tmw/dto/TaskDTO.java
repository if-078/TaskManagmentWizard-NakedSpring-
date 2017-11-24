package com.softserve.academy.tmw.dto;

import com.softserve.academy.tmw.entity.Tag;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

public class TaskDTO {

  private int id;
  private String name;
  private String createdDate;
  private String startDate;
  private String endDate;
  private String estimateTime;
  private int assignTo;
  private int statusId;
  private int priorityId;
  private int parentId;
  private Tag tags[];

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
  public String getStartDate() {
    return startDate;
  }

  @NotBlank
  @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
  public String getEndDate() {
    return endDate;
  }

//  @NotBlank
//  @Pattern(regexp = "^\\d:\\d{2}:\\d{2}")
  public String getEstimateTime() {
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

  public Tag[] getTags() {
    return tags;
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

  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }

  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }

  public void setEstimateTime(String estimateTime) {
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

  public void setTags(Tag[] tags) {
    this.tags = tags;
  }
}
