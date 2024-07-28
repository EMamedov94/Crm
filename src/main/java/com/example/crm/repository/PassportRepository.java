<<<<<<< HEAD
package com.example.crm.repository;

import com.example.crm.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    boolean existsByPassportNumber(String passportNumber);
}
=======
package com.example.crm.repository;

import com.example.crm.entity.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
    boolean existsByPassportNumber(String passportNumber);
}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
