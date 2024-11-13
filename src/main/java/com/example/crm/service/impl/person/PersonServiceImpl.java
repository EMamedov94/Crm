package com.example.crm.service.impl.person;

import com.example.crm.entity.Passport;
import com.example.crm.entity.Person;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.person.PersonService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    // Add new person
    @Override
    public Person addNewPerson(Person person) {
        person.setBalance(0.0);
        return personRepository.save(person);
    }

    // Find person by Passport number
    @Override
    @Transactional
    public Person findPersonByPassportNumber(Passport passportNumber) {
        Person personDb = personRepository.findByPassportPassportNumber(passportNumber.getPassportNumber());

        if (personDb == null) {
            throw new RuntimeException("Ничего не найдено");
        }

        Hibernate.initialize(personDb.getDeposits());

        return personDb;
    }

    // Find person by Phone number
    @Override
    public Person findPersonByPhoneNumber(String phoneNumber) {
        return personRepository.findByPhoneNumber(phoneNumber);
    }
}
