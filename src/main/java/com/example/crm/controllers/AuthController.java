package com.example.crm.controllers;

import com.example.crm.entity.users.Member;
import com.example.crm.exception.InvalidPasswordException;
import com.example.crm.exception.UserAlreadyExistsException;
import com.example.crm.exception.UserNotFoundException;
import com.example.crm.service.AuthenticationService;
import com.example.crm.service.ValidationService;
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
    private final ValidationService validationService;

    // Registration new user
    @PostMapping("/registrationNewMember")
    public ResponseEntity<Object> registrationNewMember(@RequestBody Member member) {
        try {
            if (validationService.validateNewMember(member)) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(authenticationService.registrationNewMember(member));
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Произошло что то неизвестное :(");
    }

    // Login user
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Member member) {
        try {
            if (validationService.validateCredentials(member)) {
                return ResponseEntity
                        .status(HttpStatus.OK)
                        .body(authenticationService.loginMember(member));
            }
        } catch (UserNotFoundException e) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(e.getMessage());
        } catch (InvalidPasswordException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Слишком много попыток входа, попробуйте позже");
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Произошло что то неизвестное :(");
    }
}
