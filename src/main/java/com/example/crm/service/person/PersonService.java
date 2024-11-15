package com.example.crm.service.person;

import com.example.crm.entity.Passport;
import com.example.crm.entity.Person;

public interface PersonService {
    Person addNewPerson(Person person);
    Person findPersonByPassportNumber(String passportNumber);
    Person findPersonByPhoneNumber(String phoneNumber);
}
