package com.example.crm.repository;

import com.example.crm.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    boolean existsByPassportNumber(String passportNumber);
}
