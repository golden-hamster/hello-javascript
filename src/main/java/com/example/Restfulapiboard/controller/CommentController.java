package com.example.Restfulapiboard.controller;

import com.example.Restfulapiboard.dto.MemberDto;
import com.example.Restfulapiboard.dto.request.CommentRequest;
import com.example.Restfulapiboard.dto.response.CommentResponse;
import com.example.Restfulapiboard.dto.response.CommentsResponse;
import com.example.Restfulapiboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class CommentController {

    private final CommentService commentService;

    @GetMapping("/articles/{articleId}/comments")
    public ResponseEntity<CommentsResponse> findComments(@PathVariable Long articleId) {
        List<CommentResponse> comments = commentService.findByArticleId(articleId).stream().map(CommentResponse::from).toList();
        CommentsResponse commentsResponse = CommentsResponse.from(comments);
        return ResponseEntity.ok(commentsResponse);
    }

    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Void> saveComment(@PathVariable Long articleId,
                                            @RequestBody CommentRequest commentRequest,
                                            @AuthenticationPrincipal MemberDto memberDto) {
        Long commentId = commentService.saveComment(articleId, commentRequest.toDto(), memberDto);
        return ResponseEntity.created(URI.create("/comments/" + commentId)).build();
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              @AuthenticationPrincipal MemberDto memberDto) {
        commentService.deleteComment(commentId, memberDto);
        return ResponseEntity.noContent().build();
    }

}
