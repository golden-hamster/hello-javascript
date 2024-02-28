package com.example.Restfulapiboard.dto.response;

import com.example.Restfulapiboard.dto.ArticleDto;

import java.time.LocalDateTime;

public record ArticleResponse(
        Long id,
        String title,
        String content,
        String createdBy,
        LocalDateTime createdAt
) {

    public static ArticleResponse from(ArticleDto articleDto) {
        return new ArticleResponse(
                articleDto.id(),
                articleDto.title(),
                articleDto.content(),
                articleDto.createdBy(),
                articleDto.createdAt()
        );
    }
}
