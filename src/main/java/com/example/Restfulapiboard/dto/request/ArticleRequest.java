package com.example.Restfulapiboard.dto.request;

import com.example.Restfulapiboard.dto.ArticleDto;
import com.example.Restfulapiboard.dto.MemberDto;

import java.io.Serializable;

public record ArticleRequest(
        String title,
        String content
){

    public ArticleDto toDto(MemberDto memberDto) {
        return ArticleDto.of(
                memberDto,
                title,
                content
        );
    }
}