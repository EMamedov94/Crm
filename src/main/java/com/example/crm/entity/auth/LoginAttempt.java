<<<<<<< HEAD
package com.example.crm.entity.auth;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class LoginAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(nullable = false)
    private int attempts;
    private LocalDateTime lastAttempts;

}
=======
package com.example.crm.entity.auth;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class LoginAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    @Column(nullable = false)
    private int attempts;
    private LocalDateTime lastAttempts;

}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
