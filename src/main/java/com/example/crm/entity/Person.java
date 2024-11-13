package com.example.crm.entity;

import com.example.crm.entity.products.Deposit;
import com.example.crm.enums.Document;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "sur_name", nullable = true)
    private String surName;

    @Column(name = "phone_number")
    private String phoneNumber;
    private Double balance;

    @Enumerated(EnumType.STRING)
    private Document document;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    @JsonManagedReference
    private Passport passport;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    private Address address;

    @OneToMany(mappedBy = "depositHolder")
    @JsonManagedReference
    private Set<Deposit> deposits;
}
