package com.example.Restfulapiboard.config;

import com.example.Restfulapiboard.domain.Member;
import com.example.Restfulapiboard.dto.MemberDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//                 인증되지 않은 사용자인 경우
            if (authentication == null || !authentication.isAuthenticated()) {
                return Optional.empty();
            }

//                 인증된 사용자인 경우
            Object principal = authentication.getPrincipal();
            if (principal instanceof MemberDto) {
                return Optional.of(((MemberDto) principal).getUsername());
            }
            return Optional.empty();
        };
    }
}

