<<<<<<< HEAD
package com.example.crm.repository;

import com.example.crm.entity.auth.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
    LoginAttempt findByUsername(String username);
}
=======
package com.example.crm.repository;

import com.example.crm.entity.auth.LoginAttempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginAttemptRepository extends JpaRepository<LoginAttempt, Long> {
    LoginAttempt findByUsername(String username);
}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
