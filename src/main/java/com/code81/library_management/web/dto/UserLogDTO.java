package com.code81.library_management.web.dto;

import java.sql.Timestamp;

public class UserLogDTO {
    private String username;
    private String fullName;
    private String role;
    private String action;
    private Timestamp timestamp;

    public UserLogDTO(String username, String fullName, String role, String action, Timestamp timestamp) {
        this.username = username;
        this.fullName = fullName;
        this.role = role;
        this.action = action;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRole() {
        return role;
    }

    public String getAction() {
        return action;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
