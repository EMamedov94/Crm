package com.example.crm.controllers;


import com.example.crm.entity.Person;
import com.example.crm.exception.ValidationException;
import com.example.crm.service.person.PersonService;
import com.example.crm.service.person.ValidationPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;
    private final ValidationPerson validationPerson;

    // Add new person
    @PostMapping("/addNewPerson")
    public ResponseEntity<Object> addNewPerson(@RequestBody Person person) {
        try {
            if (validationPerson.validateNewPerson(person)) {
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body(personService.addNewPerson(person));
            }
        } catch (ValidationException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(e.getErrors());
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Произошло что то неизвестное :(");
    }

    // Find person by phone number
    @GetMapping("/findPersonByPhoneNumber")
    public ResponseEntity<Object> getPersonByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.findPersonByPhoneNumber(phoneNumber));
    }

    // Find person by passport number
    @GetMapping("/findPersonByPassportNumber")
    public ResponseEntity<Object> getPersonByPassportNumber(@RequestParam String passportNumber) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.findPersonByPassportNumber(passportNumber));
    }
}
