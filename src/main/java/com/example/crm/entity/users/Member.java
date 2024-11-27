package com.example.crm.entity.users;

import com.example.crm.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@Builder
public class Member implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Логин не может быть пустым")
    @Size(min = 6, message = "Логин должен содержать минимум 6 символов")
    private String username;

    @NotNull(message = "Пароль не может быть пустым")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Пароль должен содержать минимум одну заглавную букву, одну строчную букву, одну цифру и быть не менее 8 символов")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    @Transient
    private String token;

    public Member() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }
}
