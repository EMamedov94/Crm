package com.example.crm.repository;

import com.example.crm.entity.auth.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
    LoginAttempt findByUsername(String username);
}
