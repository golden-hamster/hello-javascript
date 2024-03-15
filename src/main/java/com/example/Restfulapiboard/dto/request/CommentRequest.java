package com.example.Restfulapiboard.dto.request;

import com.example.Restfulapiboard.dto.ArticleDto;
import com.example.Restfulapiboard.dto.CommentDto;
import com.example.Restfulapiboard.dto.MemberDto;

public record CommentRequest(
        String content
) {

    public CommentDto toDto() {
        return CommentDto.of(content, null, null);
    }
}
