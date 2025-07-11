package com.code81.library_management.logic.service_impl;

import com.code81.library_management.data.entity.SystemUser;
import com.code81.library_management.data.entity.UserLog;
import com.code81.library_management.data.repository.UserLogRepository;
import com.code81.library_management.logic.service.UserLogService;
import org.springframework.stereotype.Service;

@Service
public class UserLogServiceImpl implements UserLogService {
    private final UserLogRepository userLogRepository;

    public UserLogServiceImpl(UserLogRepository userLogRepository) {
        this.userLogRepository = userLogRepository;
    }

    @Override
    public void logAction(SystemUser user, String action) {
        UserLog log = new UserLog(user, action);
        userLogRepository.save(log);
    }
}
