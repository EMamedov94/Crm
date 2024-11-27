package com.example.crm.controllers;

import com.example.crm.entity.users.Member;
import com.example.crm.service.auth.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    // Регистрация нового пользователя
    @PostMapping("/registrationNewMember")
    public ResponseEntity<Object> registrationNewMember(@RequestBody @Valid Member member) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(authenticationService.registrationNewMember(member));
    }

    // Вход пользователя
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid Member member) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(authenticationService.loginMember(member));
    }
}
