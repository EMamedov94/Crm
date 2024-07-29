package com.example.crm.repository;

import com.example.crm.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByPassportPassportNumber(String passportNumber);
    Person findByPhoneNumber(String phoneNumber);
    @Query("SELECT p.balance FROM Person p WHERE p.id = :clientId")
    Double findBalanceByClientId(@Param("clientId") Long clientId);
    boolean existsByPhoneNumber(String phoneNumber);
}
