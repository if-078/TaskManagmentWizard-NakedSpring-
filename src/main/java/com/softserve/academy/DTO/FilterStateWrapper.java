package com.softserve.academy.DTO;

public class FilterStateWrapper {
    private int id;
    private int[] statuses;
    private int[] tags;
    private int[] priorities;

    public FilterStateWrapper() {

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