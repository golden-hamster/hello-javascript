package com.example.Restfulapiboard.dto.response;

import org.springframework.data.domain.Page;

public record ArticlesResponse(
        Page<ArticleResponse> articleResponses
) {
    public static ArticlesResponse from(Page<ArticleResponse> articleResponses) {
        return new ArticlesResponse(articleResponses);
    }
}
