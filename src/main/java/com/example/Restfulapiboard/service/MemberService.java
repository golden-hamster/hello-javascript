package com.example.Restfulapiboard.service;

import com.example.Restfulapiboard.dto.MemberDto;
import com.example.Restfulapiboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

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

}
