package com.example.crm.service.impl;

import com.example.crm.entity.users.Member;
import com.example.crm.enums.Role;
import com.example.crm.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceImplTest {

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrationNewMember() {

        // Данные для теста
        Member member = Member.builder()
                .username("username")
                .password("Qq123321")
                .role(Role.ROLE_USER)
                .build();

        Member encodedMember = Member.builder()
                .username("username")
                .password("encodedPassword")
                .role(Role.ROLE_USER)
                .build();

        // Мокаем поведение passwordEncoder и memberRepository
        when(passwordEncoder.encode("plainPassword")).thenReturn("encodedPassword");
        when(memberRepository.save(any(Member.class))).thenReturn(encodedMember);

        // Вызов метода
        Member result = authenticationService.registrationNewMember(member);

        // Проверка результата
        assertNotNull(result);
        assertEquals("username", result.getUsername());
        assertEquals("encodedPassword", result.getPassword());
        assertEquals(Role.ROLE_USER, result.getRole());

        // Проверяем, что методы были вызваны
        verify(passwordEncoder).encode("Qq123321");
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    void loginMember() {
    }
}