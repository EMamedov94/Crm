<<<<<<< HEAD
package com.example.crm.repository;

import com.example.crm.entity.users.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);
    Member findByUsername(String username);
}
=======
package com.example.crm.repository;

import com.example.crm.entity.users.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);
    Member findByUsername(String username);
}
>>>>>>> 286b440547e40e1d18d973cda5a528304e0c05ea
