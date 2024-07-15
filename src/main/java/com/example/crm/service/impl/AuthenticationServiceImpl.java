package com.example.crm.service.impl;

import com.example.crm.entity.users.Member;
import com.example.crm.repository.MemberRepository;
import com.example.crm.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    // Registration new user
    @Override
    public Member registrationNewMember(Member member) {
        if (memberRepository.existsByUsername(member.getUsername())) {
            throw new RuntimeException("username is already registered");
        }



        return memberRepository.save(member);
    }
}
