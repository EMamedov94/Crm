package com.example.crm.service;

import com.example.crm.entity.users.Member;

public interface ValidationService {
    boolean validateCredentials(Member member);
    boolean validateNewMember(Member member);
}
