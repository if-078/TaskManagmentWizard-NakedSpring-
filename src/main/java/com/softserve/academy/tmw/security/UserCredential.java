package com.softserve.academy.tmw.security;

public class UserCredential {
    private String userEmail;
    private String password;

    public String getUserEmail() {
        return userEmail;
    }

    public void setLogin(String username) {
        this.userEmail = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
