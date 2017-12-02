package com.softserve.academy.tmw.dao.util.wrapper;

import java.util.List;

public class FilterStateWrapper {

  private int parentId;
  private int userId;
  private String[] dates;
  private int[] statuses;
  private int[] tags;
  private int[] priorities;
  private boolean isPlanned;
  private List<Integer> idS;

  public FilterStateWrapper() {
  }

  public List<Integer> getIdS() {
    return idS;
  }

  public void setIdS(List<Integer> idS) {
    this.idS = idS;
  }

  public String[] getDates() {
    return dates;
  }

  public void setDates(String[] dates) {
    this.dates = dates;
  }

  public void setParentId(int parentId) {
    this.parentId = parentId;
  }

  public void setStatus(int[] statuses) {
    this.statuses = statuses;
  }

  public void setTag(int[] tags) {
    this.tags = tags;
  }

  public void setPriority(int[] priorities) {
    this.priorities = priorities;
  }

  public int getParentId() {
    return parentId;
  }

  public int[] getStatus() {
    return statuses;
  }

  public int[] getTag() {
    return tags;
  }

  public int[] getPriority() {
    return priorities;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public boolean isPlanned() {

    return isPlanned;
  }

  public void setPlanned(boolean planned) {
    isPlanned = planned;
  }

}