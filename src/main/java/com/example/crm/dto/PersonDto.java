package com.example.crm.dto;

import com.example.crm.entity.Address;
import com.example.crm.entity.Passport;
import lombok.Data;

@Data
public class PersonDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String surName;
    private String phoneNumber;
    private String document;
    private Passport passport;
    private Address address;
}
