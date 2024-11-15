package com.example.crm.service.impl.person;

import com.example.crm.entity.Passport;
import com.example.crm.entity.Person;
import com.example.crm.exception.UserNotFoundException;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.person.PersonService;
import com.example.crm.utils.PersonSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    // Добавление нового клиента
    @Override
    public Person addNewPerson(Person person) {
        person.setBalance(0.0);
        return personRepository.save(person);
    }

    // Поиск клиента по номеру паспорта
    @Override
    @Transactional
    public Person findPersonByPassportNumber(String passportNumber) {
        return findPersonOrThrow(passportNumber);
    }

    // Поиск клиента по номеру телефона
    @Override
    @Transactional
    public Person findPersonByPhoneNumber(String phoneNumber) {
        return findPersonOrThrow(phoneNumber);
    }

    // Поиск клиента в бд по полученным данным и проверка на NULL
    private Person findPersonOrThrow(String searchMethod) {
        Person personDb = personRepository.findOne(PersonSpecification.bySearchValue(searchMethod))
                .orElseThrow(UserNotFoundException::new);
        Hibernate.initialize(personDb.getDeposits());
        return personDb;
    }
}
