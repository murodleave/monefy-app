package com.monefy.app.repos;

import com.monefy.app.entities.EdsPermission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepo extends JpaRepository<EdsPermission, Long> {
    Optional<EdsPermission> findByCode(String name);
}
