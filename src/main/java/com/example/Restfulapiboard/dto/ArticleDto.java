package com.example.Restfulapiboard.dto;

import com.example.Restfulapiboard.domain.Article;
import com.example.Restfulapiboard.domain.Member;

import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        MemberDto memberDto,
        String title,
        String content,
        LocalDateTime createdAt,
        String createdBy,
        String modifiedBy,
        LocalDateTime modifiedAt,
        Integer commentsCount
) {
    public static ArticleDto of(MemberDto memberDto, String title, String content) {
        return new ArticleDto(null, memberDto, title, content, null, null, null, null, null);
    }

    public static ArticleDto of(Long id, MemberDto memberDto, String title, String content, LocalDateTime createdAt, String createdBy, String modifiedBy, LocalDateTime modifiedAt, Integer commentsCount) {
        return new ArticleDto(id, memberDto, title, content, createdAt, createdBy, modifiedBy, modifiedAt, commentsCount);
    }

    public static ArticleDto from(Article entity) {
        return new ArticleDto(
                entity.getId(),
                MemberDto.from(entity.getMember()),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy(),
                entity.getModifiedAt(),
                entity.getCommentsCount()
        );
    }

    public Article toEntity(Member member) {
        return Article.of(
                member,
                title,
                content
        );
    }
}