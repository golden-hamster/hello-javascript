package com.example.Restfulapiboard.controller;

import com.example.Restfulapiboard.dto.response.CommentResponse;
import com.example.Restfulapiboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/comments")
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/{articleId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long articleId) {
        List<CommentResponse> comments = commentService.findComments(articleId).stream().map(CommentResponse::from).toList();
        return ResponseEntity.ok(comments);
    }
}
