package com.example.Restfulapiboard.dto.request;

import com.example.Restfulapiboard.dto.MemberDto;

public record MemberRequest(
        String username,
        String password
) {
    public static MemberRequest of(String username, String password) {
        return new MemberRequest(username, password);
    }

    public MemberDto toDto() {
        return MemberDto.of(username, password);
    }
}
