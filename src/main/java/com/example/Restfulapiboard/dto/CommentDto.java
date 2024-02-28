package com.example.Restfulapiboard.dto;

import com.example.Restfulapiboard.domain.Article;
import com.example.Restfulapiboard.domain.Comment;
import com.example.Restfulapiboard.domain.Member;

import java.io.Serializable;
import java.time.LocalDateTime;

public record CommentDto(
        Long id,
        String content,
        MemberDto memberDto,
        ArticleDto articleDto,
        LocalDateTime createdAt,
        String createdBy,
        String modifiedBy,
        LocalDateTime modifiedAt
){

    public static CommentDto of(String content, MemberDto memberDto, ArticleDto articleDto) {
        return new CommentDto(null, content, memberDto, articleDto, null, null, null, null);
    }

    public static CommentDto of(Long id, String content, MemberDto memberDto, ArticleDto articleDto, LocalDateTime createdAt, String createdBy, String modifiedBy, LocalDateTime modifiedAt) {
        return new CommentDto(id, content, memberDto, articleDto, createdAt, createdBy, modifiedBy, modifiedAt);
    }

    public static CommentDto from(Comment entity) {
        return new CommentDto(
                entity.getId(),
                entity.getContent(),
                MemberDto.from(entity.getMember()),
                ArticleDto.from(entity.getArticle()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedBy(),
                entity.getModifiedAt()
        );
    }

    public Comment toEntity(Member member, Article article) {
        return Comment.of(
                content,
                member,
                article
        );
    }
}