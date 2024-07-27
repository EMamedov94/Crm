package com.example.crm.service;

import com.example.crm.entity.Person;

public interface PersonSearchService {
    Person findPersonByPassportNumber(String passportNumber);
    Person findPersonByPhoneNumber(String phoneNumber);
}
