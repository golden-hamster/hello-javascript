package com.example.Restfulapiboard.dto;

import com.example.Restfulapiboard.domain.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public record MemberDto(
        Long id,
        String username,
        String password
)implements UserDetails {

    public static MemberDto of(String username, String password) {
        return new MemberDto(null, username, password);
    }

    public static MemberDto of(Long id, String username, String password) {
        return new MemberDto(id, username, password);
    }

    public static MemberDto from(Member entity) {
        return new MemberDto(
                entity.getId(),
                entity.getUsername(),
                entity.getPassword()
        );
    }

    public Member toEntity() {
        return Member.of(
                username,
                password
        );
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}