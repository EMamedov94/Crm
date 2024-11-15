package com.example.crm.exception;

public class InvalidDepositStatusException extends RuntimeException {
    public InvalidDepositStatusException() {
        super("Депозит уже закрыт");
    }
}
