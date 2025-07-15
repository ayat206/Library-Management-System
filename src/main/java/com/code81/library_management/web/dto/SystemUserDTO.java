package com.code81.library_management.web.dto;

public class SystemUserDTO {
    private Long id;
    private String username;
    private String fullName;
    private String roleName;

    public SystemUserDTO(Long id, String username, String fullName, String roleName) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.roleName = roleName;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getRoleName() {
        return roleName;
    }

}
