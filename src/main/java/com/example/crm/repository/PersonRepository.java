package com.example.crm.repository;

import com.example.crm.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByPassportPassportNumber(String passportNumber);
    Person findByPhoneNumber(String phoneNumber);
}
