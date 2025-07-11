package com.code81.library_management.web.controller;

import com.code81.library_management.data.entity.SystemUser;
import com.code81.library_management.data.entity.UserLog;
import com.code81.library_management.data.repository.UserLogRepository;
import com.code81.library_management.security.auth.CustomUserDetails;
import com.code81.library_management.web.dto.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserLogRepository userLogRepository;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserLogRepository userLogRepository) {
        this.authenticationManager = authenticationManager;
        this.userLogRepository = userLogRepository;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            if (auth.isAuthenticated()) {
                // Log the login
                CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
                SystemUser user = userDetails.getSystemUser();

                UserLog log = new UserLog(user, "Logged in");
                userLogRepository.save(log);

                return "Login successful!";
            } else {
                return "Invalid credentials";
            }

        } catch (AuthenticationException e) {
            return "Login failed: " + e.getMessage();
        }
    }
}
