package com.example.Restfulapiboard.dto.response;

import java.util.List;

public record CommentsResponse(
        List<CommentResponse> comments,
        int totalCount
) {

    public static CommentsResponse from(List<CommentResponse> commentResponseList, int totalCount) {
        return new CommentsResponse(commentResponseList, totalCount);
    }
}
