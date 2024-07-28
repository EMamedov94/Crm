<<<<<<< HEAD
package com.example.crm.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String passportNumber;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @Column(nullable = false)
    private String issuingAuthority;

    @OneToOne()
    @JoinColumn(name = "person_id")
    @JsonBackReference
    private Person person;
}
=======
package com.example.crm.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String passportNumber;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @Column(nullable = false)
    private String issuingAuthority;

    @OneToOne()
    @JoinColumn(name = "person_id")
    private Person person;
}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
