package com.example.crm.controllers;


import com.example.crm.entity.Passport;
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
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addNewPerson(@RequestBody Person person,
                                               @AuthenticationPrincipal UserDetails userDetails) {
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
    @GetMapping("/findPersonByPhoneNumber/{phoneNumber}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> getPersonByPhoneNumber(@PathVariable String phoneNumber,
                                                         @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.findPersonByPhoneNumber(phoneNumber));
    }

    // Find person by passport number
    @GetMapping("/findPersonByPassportNumber")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> getPersonByPassportNumber(@RequestBody Passport passportNumber,
                                                            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(personService.findPersonByPassportNumber(passportNumber));
    }

}
