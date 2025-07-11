package com.code81.library_management.data.repository;

import com.code81.library_management.data.entity.UserLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}
