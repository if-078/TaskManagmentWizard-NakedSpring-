package com.softserve.academy.tmw.entity;

public class JiraCredential {
    private String url;
    private String creds;
    private String name;
    private String key;
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCreds() {
        return creds;
    }

    public void setCreds(String creds) {
        this.creds = creds;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
