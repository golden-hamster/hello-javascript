package com.example.Restfulapiboard.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http
    ) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 정적 리소스 허용
                        .mvcMatchers(   // 특정 경로에 대한 접근 허용
                                HttpMethod.GET,
                                "/",
                                "/api/articles",
                                "/api/articles/{}",
                                "/api/articles/search-hashtag",
                                "/api/articles/{}/comments",
                                "/members/form",
                                "/members"
                        ).permitAll()
                        .mvcMatchers(HttpMethod.POST,
                                "/users/form")
                        .permitAll()
                        .anyRequest().authenticated()  // 나머지 요청은 인증된 사용자만 허용
                )
                .formLogin(withDefaults()) // 기본 로그인 폼 사용
                .logout(logout -> logout.logoutSuccessUrl("/")) // 로그아웃 설정
                .csrf().disable()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}