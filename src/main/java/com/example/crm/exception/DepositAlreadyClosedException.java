package com.example.crm.exception;

public class DepositAlreadyClosedException extends RuntimeException {
    public DepositAlreadyClosedException() {
        super("Депозит уже закрыт");
    }
}
