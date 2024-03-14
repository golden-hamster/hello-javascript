package com.example.Restfulapiboard.controller;

import com.example.Restfulapiboard.dto.response.CommentResponse;
import com.example.Restfulapiboard.dto.response.CommentsResponse;
import com.example.Restfulapiboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{articleId}/comments")
    public ResponseEntity<CommentsResponse> findComments(@PathVariable Long articleId) {
        List<CommentResponse> comments = commentService.findByArticleId(articleId).stream().map(CommentResponse::from).toList();
        CommentsResponse commentsResponse = CommentsResponse.from(comments);
        return ResponseEntity.ok(commentsResponse);
    }

}
