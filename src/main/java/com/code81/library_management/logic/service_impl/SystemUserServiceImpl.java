package com.code81.library_management.logic.service_impl;

import com.code81.library_management.data.entity.SystemUser;
import com.code81.library_management.data.repository.SystemUserRepository;
import com.code81.library_management.logic.service.SystemUserService;
import com.code81.library_management.web.dto.SystemUserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<SystemUserDTO> getAllUsers() {
        return systemUserRepository.findAll().stream()
                .map(user -> new SystemUserDTO(
                        user.getId(),
                        user.getUsername(),
                        user.getFullName(),
                        user.getRole().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public SystemUserDTO getUserById(Long id) {
        SystemUser user = systemUserRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new SystemUserDTO(
                user.getId(),
                user.getUsername(),
                user.getFullName(),
                user.getRole().getName()
        );
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
