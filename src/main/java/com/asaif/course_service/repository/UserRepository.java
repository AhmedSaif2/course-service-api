package com.asaif.course_service.repository;

import com.asaif.course_service.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, String> {
    AppUser findByUsername(String username);
}
