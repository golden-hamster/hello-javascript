package com.example.Restfulapiboard.dto.response;

import com.example.Restfulapiboard.dto.CommentDto;

import java.time.LocalDateTime;

public record CommentResponse(
        Long id,
        String content,
        String createdBy,
        LocalDateTime createdAt
){

    public static CommentResponse from(CommentDto commentDto) {
        return new CommentResponse(
                commentDto.id(),
                commentDto.content(),
                commentDto.createdBy(),
                commentDto.createdAt()
        );
    }
}