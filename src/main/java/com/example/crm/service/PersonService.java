package com.example.crm.service;

import com.example.crm.entity.Passport;
import com.example.crm.entity.Person;

public interface PersonService {
    Person addNewPerson(Person person);
    Person findPersonByPassportNumber(Passport passportNumber);
    Person findPersonByPhoneNumber(String phoneNumber);
}
