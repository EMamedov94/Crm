package com.example.crm.repository;

import com.example.crm.entity.users.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUsername(String username);
    Member findByUsername(String username);
}
