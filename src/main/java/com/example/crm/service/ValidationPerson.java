package com.example.crm.service;

import com.example.crm.entity.Person;

public interface ValidationPerson {
    void validatePassport(String passportNumber);
    void validatePhoneNumber(String phoneNumber);
    boolean validateNewPerson(Person person);
}
