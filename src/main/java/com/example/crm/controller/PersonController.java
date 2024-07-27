package com.example.crm.controller;

import com.example.crm.entity.Address;
import com.example.crm.entity.Passport;
import com.example.crm.entity.Person;
import com.example.crm.repository.AddressRepository;
import com.example.crm.repository.PassportRepository;
import com.example.crm.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    // Add new person
    @PostMapping("/addNewPerson")
    public ResponseEntity<Person> addNewPerson(@RequestBody Person person) {
        Passport passport = person.getPassport();
        if (passport != null) {
            passport.setPerson(person);
        }

        Address address = person.getAddress();
        if (address != null) {
            address.setPerson(person);
        }

        return ResponseEntity.ok(personService.addNewPerson(person));
    }

    // Find person by phone number
    @GetMapping("/findPersonByPhoneNumber/{phoneNumber}")
    public ResponseEntity<Person> getPersonByPhoneNumber(@PathVariable String phoneNumber) {
        Person person = personService.findPersonByPhoneNumber(phoneNumber);

        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Find person by passport number
    @GetMapping("/findPersonByPassportNumber/{passportNumber}")
    public ResponseEntity<Person> getPersonByPassportNumber(@PathVariable String passportNumber) {
        Person person = personService.findPersonByPassportNumber(passportNumber);

        if (person != null) {
            return ResponseEntity.ok(person);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
