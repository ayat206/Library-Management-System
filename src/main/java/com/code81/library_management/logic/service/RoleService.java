package com.code81.library_management.logic.service;

import com.code81.library_management.data.entity.Role;

import java.util.List;

public interface RoleService {
    Role createRole(Role role);
    Role getRoleByName(String name);
    List<Role> getAllRoles();
}
