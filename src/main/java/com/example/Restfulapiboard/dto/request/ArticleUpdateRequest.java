package com.example.Restfulapiboard.dto.request;

import com.example.Restfulapiboard.dto.ArticleDto;
import com.example.Restfulapiboard.dto.MemberDto;

public record ArticleUpdateRequest(
        String title,
        String content
) {

    public static ArticleUpdateRequest of(String title, String content) {
        return new ArticleUpdateRequest(title, content);
    }

    public ArticleDto toDto(MemberDto memberDto) {
        return ArticleDto.of(
                memberDto,
                title,
                content);
    }
}
