package com.softserve.academy.tmw.wrapper;

public class FilterStateWrapper {
    private int id;
    private String [] dates;
    private int[] statuses;
    private int[] tags;
    private int[] priorities;

    public FilterStateWrapper() {}

    public String[] getDates() {
        return dates;
    }

    public void setDates(String[] dates) {
        this.dates = dates;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getId() {
        return id;
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

}