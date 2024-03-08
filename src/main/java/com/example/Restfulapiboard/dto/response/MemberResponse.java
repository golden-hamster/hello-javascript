package com.example.Restfulapiboard.dto.response;

import com.example.Restfulapiboard.dto.MemberDto;

public record MemberResponse(
        Long id,
        String username
) {
    public static MemberResponse from(MemberDto memberDto) {
        return new MemberResponse(memberDto.id(), memberDto.username());
    }
}
