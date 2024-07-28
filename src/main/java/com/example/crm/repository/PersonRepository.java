<<<<<<< HEAD
package com.example.crm.repository;

import com.example.crm.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByPassportPassportNumber(String passportNumber);
    Person findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
=======
package com.example.crm.repository;

import com.example.crm.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findByPassportPassportNumber(String passportNumber);
    Person findByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumber(String phoneNumber);
}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
