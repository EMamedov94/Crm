package com.example.crm.service.auth;

public interface LoginAttemptService {
    void loginSucceeded(String username);
    void loginFailed(String username);
    boolean isBlocked(String username);
}
