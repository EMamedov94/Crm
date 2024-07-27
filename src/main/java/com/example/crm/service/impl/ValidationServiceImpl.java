package com.example.crm.service.impl;

import com.example.crm.entity.users.Member;
import com.example.crm.exception.InvalidPasswordException;
import com.example.crm.exception.UserNotFoundException;
import com.example.crm.exception.ValidationException;
import com.example.crm.repository.MemberRepository;
import com.example.crm.service.LoginAttemptService;
import com.example.crm.service.ValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidationServiceImpl implements ValidationService {
    private final LoginAttemptService loginAttemptService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // Check username or password login
    @Override
    public boolean validateCredentials(Member member) {
        String username = member.getUsername();
        String password = member.getPassword();

        if (loginAttemptService.isBlocked(username)) {
            throw new RuntimeException("User is temporarily blocked");
        }

        Member userDb = memberRepository.findByUsername(username);

        if (userDb == null) {
            loginAttemptService.loginFailed(username);
            throw new UserNotFoundException("Пользователь не найден");
        }
        if (passwordEncoder.matches(password, userDb.getPassword())) {
            loginAttemptService.loginSucceeded(username);
            return true;
        } else {
            loginAttemptService.loginFailed(username);
            throw new InvalidPasswordException("Неверный пароль");
        }
    }

    // Validate new user
    @Override
    public boolean validateNewMember(Member member) {
        Member memberDb = memberRepository.findByUsername(member.getUsername());
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$";
        String loginPattern = "^[A-Za-z\\d]{6,}$";
        List<String> errors = new ArrayList<>();

        if (memberDb != null) {
            errors.add("Пользователь с ником " + member.getUsername() + " уже существует");
        }
        if (member.getUsername() == null || !Pattern.matches(loginPattern, member.getUsername())) {
            errors.add("Логин должен содержать минимум 6 символов и может содержать только латинские буквы и цифры");
        }
        if (member.getPassword() == null || !Pattern.matches(passwordPattern, member.getPassword())) {
            errors.add("Пароль должен содержать минимум одну заглавную букву, одну строчную букву, одну цифру и быть не менее 8 символов");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        return true;
    }
}
