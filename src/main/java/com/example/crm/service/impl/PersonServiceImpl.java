<<<<<<< HEAD
package com.example.crm.service.impl;

import com.example.crm.entity.Person;
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
        person.getPassport().setPerson(person);
        return personRepository.save(person);
    }

    // Find person by Passport number
    @Override
    public Person findPersonByPassportNumber(String passportNumber) {
        Person personDb = personRepository.findByPassportPassportNumber(passportNumber);

        if (personDb == null) {
            throw new RuntimeException("Ничего не найдено");
        }
        return personDb;
    }

    // Find person by Phone number
    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        return personRepository.findByPhoneNumber(phoneNumber);
    }
}
=======
package com.example.crm.service.impl;

import com.example.crm.entity.Person;
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
        Person personDb = personRepository.findByPassportPassportNumber(passportNumber);

        if (personDb == null) {
            throw new RuntimeException("Ничего не найдено");
        }
        return personDb;
    }

    // Find person by Phone number
    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        return personRepository.findByPhoneNumber(phoneNumber);
    }
}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
