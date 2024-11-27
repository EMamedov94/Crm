package com.example.crm.service.impl.auth;

import com.example.crm.configuration.JwtService;
import com.example.crm.entity.users.Member;
import com.example.crm.enums.Role;
import com.example.crm.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AuthenticationServiceImplTest {

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationServiceImpl authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registrationNewMember_ShouldSaveNewMember() {

        // Вводимые данные
        Member inputMember = new Member();
        inputMember.setUsername("username123");
        inputMember.setPassword("password123");

        // Данные которые должны быть сохранены
        Member savedMember = Member.builder()
                .username("username123")
                .password("encodedPassword123")
                .role(Role.ROLE_USER)
                .build();

        // Проверка что пароль будет зашифрован и пользователь сохранен
        when(passwordEncoder.encode("password123")).thenReturn("encodedPassword123");
        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);

        // Готовит данные что у нового пользователя будет зашифрован пароль, создает нового пользователя, сохраняет и возвращает
        Member result = authenticationService.registrationNewMember(inputMember);

        // Проверка

        // Что вернулся не null
        assertNotNull(result);

        // Что имя пользователя совпадает с ожидаемым
        assertEquals("username123", result.getUsername());

        // Что пароль зашифрован
        assertEquals("encodedPassword123", result.getPassword());

        // Что роль выставлена USER
        assertEquals(Role.ROLE_USER, result.getRole());

        // Проверка вызовов

        // Что encode был вызван с исходным паролем
        verify(passwordEncoder).encode("password123");

        // Что save был вызван 1 раз с любым классом member
        verify(memberRepository).save(any(Member.class));
    }

    @Test
    void loginMember_ShouldReturnMemberWithToken() {
        Member inputMember = new Member();
        inputMember.setUsername("username123");
        inputMember.setPassword("password123");

        Member user = Member.builder()
                .username("username123")
                .password("password123")
                .role(Role.ROLE_USER)
                .build();

        String token = "jwtToken";

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(null);
        when(memberRepository.findByUsername("username123")).thenReturn(user);
        when(jwtService.generateToken(inputMember)).thenReturn(token);

        // Когда
        Member result = authenticationService.loginMember(inputMember);

        // Тогда
        assertNotNull(result);
        assertEquals("username123", result.getUsername());
        assertEquals(token, result.getToken());

        verify(authenticationManager).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(memberRepository).findByUsername("username123");
        verify(jwtService).generateToken(inputMember);
    }
}