package com.example.crm.service.impl;

import com.example.crm.configuration.JwtService;
import com.example.crm.entity.users.Member;
import com.example.crm.repository.MemberRepository;
import com.example.crm.enums.Role;
import com.example.crm.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    // Registration new user
    @Override
    public Member registrationNewMember(Member member) {
        var newMember = Member.builder()
                .username(member.getUsername())
                .password(passwordEncoder.encode(member.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        return memberRepository.save(newMember);
    }

    // Login user
    @Override
    public Member loginMember(Member member) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        member.getUsername(),
                        member.getPassword()
                )
        );

        var user = memberRepository.findByUsername(member.getUsername());
        var token = jwtService.generateToken(member);
        user.setToken(token);

        return user;
    }
}