package com.softserve.academy.tmw.entity;

public class Tag implements NamedEntity {

    private int id;
    private String name;
    private int userId;

    public Tag(int id, String name, int userId) {
        this.id = id;
        this.name = name;
        this.userId = userId;
    }

    public Tag() {
        
    }

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public void setId(int tagId) {
        this.id = tagId;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Tag{" + "Id=" + id + ", name=" + name + ", userId=" + userId + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.userId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tag other = (Tag) obj;
        return this.id == other.id;
    }

}