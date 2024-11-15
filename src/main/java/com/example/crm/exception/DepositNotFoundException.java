package com.example.crm.exception;

public class DepositNotFoundException extends RuntimeException {
    public DepositNotFoundException() {
        super("Депозит не найден");
    }
}
