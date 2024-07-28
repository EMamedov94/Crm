package com.example.crm.service;

import com.example.crm.entity.Person;

public interface PersonService {
    Person addNewPerson(Person person);
    Person findPersonByPassportNumber(String passportNumber);
    Person findPersonByPhoneNumber(String phoneNumber);
}
