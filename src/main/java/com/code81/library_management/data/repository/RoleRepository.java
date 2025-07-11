package com.code81.library_management.data.repository;
import com.code81.library_management.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;;import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
