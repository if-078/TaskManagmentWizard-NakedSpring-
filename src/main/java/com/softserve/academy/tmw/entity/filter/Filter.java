package com.softserve.academy.tmw.entity.filter;

/**
 * Created by Oleg on 27.10.2017.
 */
public class Filter {

    private String startDate;
    private String endDate;
    private int[] statusses;
    private int[] priorities;
    private int[] tags;

    public Filter() {
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int[] getStatusses() {
        return statusses;
    }

    public void setStatusses(int[] statusses) {
        this.statusses = statusses;
    }

    public int[] getPriorities() {
        return priorities;
    }

    public void setPriorities(int[] priorities) {
        this.priorities = priorities;
    }

    public int[] getTags() {
        return tags;
    }

    public void setTags(int[] tags) {
        this.tags = tags;
    }
}
