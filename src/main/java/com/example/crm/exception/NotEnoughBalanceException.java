package com.example.crm.exception;

public class NotEnoughBalanceException extends RuntimeException {
    public NotEnoughBalanceException() {
        super("Недостаточно средств");
    }
}
