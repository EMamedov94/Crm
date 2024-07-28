package com.example.crm.service.impl;

import com.example.crm.entity.auth.LoginAttempt;
import com.example.crm.repository.LoginAttemptRepository;
import com.example.crm.service.LoginAttemptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginAttemptServiceImpl implements LoginAttemptService {
    private static final int MAX_ATTEMPTS = 3;
    private static final int LOCK_TIME_DURATION = 15; // minutes

    private final LoginAttemptRepository loginAttemptRepository;

    @Override
    public void loginSucceeded(String username) {
        LoginAttempt loginAttempt = loginAttemptRepository.findByUsername(username);
        if (loginAttempt != null) {
            loginAttempt.setAttempts(0);
            loginAttemptRepository.save(loginAttempt);
        }
    }

    @Override
    public void loginFailed(String username) {
        LoginAttempt loginAttempt = loginAttemptRepository.findByUsername(username);
        if (loginAttempt == null) {
            loginAttempt = new LoginAttempt();
            loginAttempt.setUsername(username);
            loginAttempt.setAttempts(1);
            loginAttempt.setLastAttempts(LocalDateTime.now());
        } else {
            loginAttempt.setAttempts(loginAttempt.getAttempts() + 1);
            loginAttempt.setLastAttempts(LocalDateTime.now());
        }
        loginAttemptRepository.save(loginAttempt);
    }

    @Override
    public boolean isBlocked(String username) {
        LoginAttempt loginAttempt = loginAttemptRepository.findByUsername(username);

        if (loginAttempt == null) {
            return false;
        }
        if (loginAttempt.getAttempts() >= MAX_ATTEMPTS) {
            LocalDateTime lockTime = loginAttempt.getLastAttempts().plusMinutes(LOCK_TIME_DURATION);
            if (LocalDateTime.now().isBefore(lockTime)) {
                return true;
            } else {
                loginAttempt.setAttempts(0);
                loginAttemptRepository.save(loginAttempt);
                return false;
            }
        }
        return false;
    }
}
