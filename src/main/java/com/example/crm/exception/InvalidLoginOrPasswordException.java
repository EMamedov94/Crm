package com.example.crm.exception;

public class InvalidLoginOrPasswordException extends RuntimeException {
    public InvalidLoginOrPasswordException() {
        super("Неверный логин или пароль");
    }
}
