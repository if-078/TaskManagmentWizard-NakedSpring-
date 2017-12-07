package com.softserve.academy.tmw.entity;

public class JiraCredential {

    private String jClientUrl;
    private String url;
    private String creds;
    private String name;
    private String password;

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

    public String getjClientUrl() {
        return jClientUrl;
    }

    public void setjClientUrl(String jClientUrl) {
        this.jClientUrl = jClientUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
