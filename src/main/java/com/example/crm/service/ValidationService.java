package com.example.crm.service;

import com.example.crm.entity.users.Member;

public interface ValidationService {
    Member validateCredentials(Member member);
    void validateNewMember(Member member);
}
