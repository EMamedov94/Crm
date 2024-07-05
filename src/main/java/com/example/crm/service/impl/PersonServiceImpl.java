package com.example.crm.service.impl;

import com.example.crm.entity.Person;
import com.example.crm.repository.AddressRepository;
import com.example.crm.repository.PassportRepository;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    // Add new person
    @Override
    public Person addNewPerson(Person person) {
        return personRepository.save(person);
    }

    // Find person by Passport number
    @Override
    public Person findPersonByPassportNumber(String passportNumber) {
        return personRepository.findByPassportPassportNumber(passportNumber);
    }

    // Find person by Phone number
    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        return personRepository.findByPhoneNumber(phoneNumber);
    }
}
