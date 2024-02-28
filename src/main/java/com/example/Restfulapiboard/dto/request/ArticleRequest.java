package com.example.Restfulapiboard.dto.request;

import java.io.Serializable;

public record ArticleRequest(
        String title,
        String content
){
}