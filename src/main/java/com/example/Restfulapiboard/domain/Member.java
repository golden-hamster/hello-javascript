package com.example.Restfulapiboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Getter
@ToString(callSuper = true)
@Entity
public class Member implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;


    @Override
    public String getPassword() {return null;}
    @Override public String getUsername() {return null;}
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {return null;}
    @Override public boolean isAccountNonExpired() {return false;}
    @Override public boolean isAccountNonLocked() {return false;}
    @Override public boolean isCredentialsNonExpired() {return false;}
    @Override public boolean isEnabled() {return false;}
}
