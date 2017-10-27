package com.softserve.academy.entity;

import org.springframework.security.core.GrantedAuthority;

public class Role implements GrantedAuthority {
    private int id;
    private String name;

    public Role() {}

    public Role(String name) { this.name = name; }

    public Role(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }

    @Override
    public String getAuthority() {
        return name;
    }
}