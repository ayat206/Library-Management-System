package com.code81.library_management.web.controller;

import com.code81.library_management.data.entity.Role;
import com.code81.library_management.logic.service.RoleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }


    @PostMapping
    public Role createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }


    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }
}
