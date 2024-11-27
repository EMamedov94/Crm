package com.example.crm.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Пользователь с таким логином уже существует");
    }
}
