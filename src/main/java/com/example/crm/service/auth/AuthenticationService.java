package com.example.crm.service.auth;

import com.example.crm.entity.users.Member;

public interface AuthenticationService {
    Member registrationNewMember(Member member);
    Member loginMember(Member member);
}
