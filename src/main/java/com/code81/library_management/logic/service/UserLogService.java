package com.code81.library_management.logic.service;

import com.code81.library_management.data.entity.SystemUser;

public interface UserLogService {
    void logAction(SystemUser user, String action);
}
