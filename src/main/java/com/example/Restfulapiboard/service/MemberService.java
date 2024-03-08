package com.example.Restfulapiboard.service;

import com.example.Restfulapiboard.domain.Member;
import com.example.Restfulapiboard.dto.MemberDto;
import com.example.Restfulapiboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean isUsernameExisted(String username) {
        return memberRepository.findByUsername(username).isPresent();
    }

    public MemberDto findByUsername(String username) {
        return memberRepository.findByUsername(username)
                .map(MemberDto::from)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

    public MemberDto findById(Long id) {
        return memberRepository.findById(id)
                .map(MemberDto::from)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
    }

    @Transactional
    public Long createMember(MemberDto memberDto) {
         return memberRepository.save(Member.of(memberDto.username(), passwordEncoder.encode(memberDto.password()))).getId();
    }

}
