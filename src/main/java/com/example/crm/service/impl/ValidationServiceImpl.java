package com.example.crm.service.impl;

import com.example.crm.entity.users.Member;
import com.example.crm.exception.InvalidLoginOrPasswordException;
import com.example.crm.exception.UserAlreadyExistsException;
import com.example.crm.exception.UserNotFoundException;
import com.example.crm.repository.MemberRepository;
import com.example.crm.service.auth.LoginAttemptService;
import com.example.crm.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {
    private final LoginAttemptService loginAttemptService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // Проверка логина или пароля
    @Override
    public Member validateCredentials(Member member) {
        String username = member.getUsername();
        String password = member.getPassword();

        if (loginAttemptService.isBlocked(username)) {
            throw new RuntimeException("Пользователь заблокирован");
        }

        Member userDb = memberRepository.findByUsername(username);

        if (!passwordEncoder.matches(password, userDb.getPassword())) {
            loginAttemptService.loginFailed(username);
            throw new InvalidLoginOrPasswordException();
        }

        loginAttemptService.loginSucceeded(username);
        return userDb;
    }

    // Validate new user
    @Override
    public void validateNewMember(Member member) {
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new UserAlreadyExistsException();
        }
    }
}
