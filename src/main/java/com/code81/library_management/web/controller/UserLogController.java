package com.code81.library_management.web.controller;

import com.code81.library_management.data.entity.UserLog;
import com.code81.library_management.data.repository.UserLogRepository;
import com.code81.library_management.web.dto.UserLogDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
public class UserLogController {

    private final UserLogRepository userLogRepository;

    public UserLogController(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    @GetMapping
    public List<UserLogDTO> getAllLogs() {
        return userLogRepository.findAll()
                .stream()
                .map(log -> new UserLogDTO(
                        log.getUser().getUsername(),
                        log.getUser().getFullName(),
                        log.getUser().getRole().getName(),
                        log.getAction(),
                        log.getTimestamp()
                ))
                .toList();
    }

    @GetMapping("/user/{userId}")
    public List<UserLogDTO> getLogsByUser(@PathVariable Long userId) {
        return userLogRepository.findAll()
                .stream()
                .filter(log -> log.getUser().getId().equals(userId))
                .map(log -> new UserLogDTO(
                        log.getUser().getUsername(),
                        log.getUser().getFullName(),
                        log.getUser().getRole().getName(),
                        log.getAction(),
                        log.getTimestamp()
                ))
                .toList();
    }
}
