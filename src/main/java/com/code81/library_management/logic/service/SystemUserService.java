package com.code81.library_management.logic.service;

import com.code81.library_management.data.entity.SystemUser;

import java.util.List;

public interface SystemUserService {
    SystemUser register(SystemUser systemUser); //encryption + register
    List<SystemUser> getAllUsers();
    SystemUser getUserById(Long id);
    SystemUser updateUser(Long id, SystemUser updatedUser);
    void deleteUserById(Long id);

}
