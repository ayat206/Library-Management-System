package com.code81.library_management.logic.service_impl;

import com.code81.library_management.data.entity.SystemUser;
import com.code81.library_management.data.repository.SystemUserRepository;
import com.code81.library_management.logic.service.SystemUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemUserServiceImpl implements SystemUserService {


    private final SystemUserRepository systemUserRepository;
    private final PasswordEncoder passwordEncoder;

    public SystemUserServiceImpl(SystemUserRepository systemUserRepository, PasswordEncoder passwordEncoder) {
        this.systemUserRepository = systemUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SystemUser register(SystemUser systemUser) {

        String hashedPassword = passwordEncoder.encode(systemUser.getPasswordHash());
        systemUser.setPasswordHash(hashedPassword);

        return systemUserRepository.save(systemUser);
    }

    @Override
    public List<SystemUser> getAllUsers() {
        return systemUserRepository.findAll();
    }

    @Override
    public SystemUser getUserById(Long id) {
        return systemUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public SystemUser updateUser(Long id, SystemUser updatedUser) {
        SystemUser existingUser = systemUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFullName(updatedUser.getFullName());
        existingUser.setUsername(updatedUser.getUsername());
        existingUser.setRole(updatedUser.getRole());

        if (updatedUser.getPasswordHash() != null && !updatedUser.getPasswordHash().isBlank()) {
            String hashedPassword = passwordEncoder.encode(updatedUser.getPasswordHash());
            existingUser.setPasswordHash(hashedPassword);
        }

        return systemUserRepository.save(existingUser);
    }


    @Override
    public void deleteUserById(Long id) {
        if (!systemUserRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        systemUserRepository.deleteById(id);
    }

}
