package com.example.Restfulapiboard.dto.response;

import java.util.List;

public record CommentsResponse(
        List<CommentResponse> comments
) {

    public static CommentsResponse from(List<CommentResponse> commentResponseList) {
        return new CommentsResponse(commentResponseList);
    }
}
