package com.example.crm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
public class ValidationException extends RuntimeException {
    private List<String> errors;
}
