package com.code81.library_management.web.controller;

import com.code81.library_management.data.entity.SystemUser;
import com.code81.library_management.logic.service.SystemUserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class SystemUserController {
    private final SystemUserService systemUserService;

    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
    }

    @PostMapping("/register")
    public SystemUser registerUser(@RequestBody SystemUser systemUser) {
        return systemUserService.register(systemUser);
    }

    @GetMapping
    public List<SystemUser> getAllUsers() {
        return systemUserService.getAllUsers();
    }

    @GetMapping("/{id}")
    public SystemUser getUserById(@PathVariable Long id) {
        return systemUserService.getUserById(id);
    }

    @PutMapping("/{id}")
    public SystemUser updateUser(@PathVariable Long id, @RequestBody SystemUser updatedUser) {
        return systemUserService.updateUser(id, updatedUser);
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        systemUserService.deleteUserById(id);
    }

}
