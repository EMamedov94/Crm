<<<<<<< HEAD
package com.example.crm.service.impl;

import com.example.crm.entity.Person;
import com.example.crm.exception.ValidationException;
import com.example.crm.repository.PassportRepository;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.ValidationPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidationPersonImpl implements ValidationPerson {

    private final PassportRepository passportRepository;
    private final PersonRepository personRepository;

    // Проверка правильности паспорта
    @Override
    public void validatePassport(String passportNumber) {
        List<String> errors = new ArrayList<>();
        String passportPattern = "^\\d{10}$";

        if (passportNumber == null || !Pattern.matches(passportPattern, passportNumber)) {
            errors.add("Неверный формат номера паспорта");
        }

        if (passportRepository.existsByPassportNumber(passportNumber)) {
            errors.add("Такой номер паспорта уже используется");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    // Проверка правильности номера телефона
    @Override
    public void validatePhoneNumber(String phoneNumber) {
        String phoneNumberPattern = "^9\\d{9}$";
        List<String> errors = new ArrayList<>();

        if (phoneNumber == null || !Pattern.matches(phoneNumberPattern, phoneNumber)) {
            errors.add("Номер телефона должен начинаться с 9 и содержать 10 цифр.");
        }

        if (personRepository.existsByPhoneNumber(phoneNumber)) {
            errors.add("Такой номер телефона уже используется");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    // Сбор всех ошибок в одну валидацию
    @Override
    public boolean validateNewPerson(Person person) {
        List<String> errors = new ArrayList<>();

        try {
            validatePassport(person.getPassport().getPassportNumber());
        } catch (ValidationException e) {
            errors.addAll(e.getErrors());
        }

        try {
            validatePhoneNumber(person.getPhoneNumber());
        } catch (ValidationException e) {
            errors.addAll(e.getErrors());
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        return true;
    }
}
=======
package com.example.crm.service.impl;

import com.example.crm.entity.Person;
import com.example.crm.exception.ValidationException;
import com.example.crm.repository.PassportRepository;
import com.example.crm.repository.PersonRepository;
import com.example.crm.service.ValidationPerson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class ValidationPersonImpl implements ValidationPerson {

    private final PassportRepository passportRepository;
    private final PersonRepository personRepository;

    // Проверка правильности паспорта
    @Override
    public void validatePassport(String passportNumber) {
        List<String> errors = new ArrayList<>();
        String passportPattern = "^\\d{10}$";

        if (passportNumber == null || !Pattern.matches(passportPattern, passportNumber)) {
            errors.add("Неверный формат номера паспорта");
        }

        if (passportRepository.existsByPassportNumber(passportNumber)) {
            errors.add("Такой номер паспорта уже используется");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    // Проверка правильности номера телефона
    @Override
    public void validatePhoneNumber(String phoneNumber) {
        String phoneNumberPattern = "^9\\d{9}$";
        List<String> errors = new ArrayList<>();

        if (phoneNumber == null || !Pattern.matches(phoneNumberPattern, phoneNumber)) {
            errors.add("Номер телефона должен начинаться с 9 и содержать 10 цифр.");
        }

        if (personRepository.existsByPhoneNumber(phoneNumber)) {
            errors.add("Такой номер телефона уже используется");
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    // Сбор всех ошибок в одну валидацию
    @Override
    public boolean validateNewPerson(Person person) {
        List<String> errors = new ArrayList<>();

        try {
            validatePassport(person.getPassport().getPassportNumber());
        } catch (ValidationException e) {
            errors.addAll(e.getErrors());
        }

        try {
            validatePhoneNumber(person.getPhoneNumber());
        } catch (ValidationException e) {
            errors.addAll(e.getErrors());
        }

        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }

        return true;
    }
}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
