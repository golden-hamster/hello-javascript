package com.example.Restfulapiboard.controller;

import com.example.Restfulapiboard.dto.MemberDto;
import com.example.Restfulapiboard.dto.request.ArticleRequest;
import com.example.Restfulapiboard.dto.request.ArticleUpdateRequest;
import com.example.Restfulapiboard.dto.response.ArticleResponse;
import com.example.Restfulapiboard.dto.response.ArticlesResponse;
import com.example.Restfulapiboard.service.ArticleService;
import com.example.Restfulapiboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RequestMapping("/api/articles")
@RestController
public class ArticleController {

    private final ArticleService articleService;
    private final CommentService commentService;

    @GetMapping
    public ResponseEntity<ArticlesResponse> findArticles(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ArticleResponse> articles = articleService.findAll(pageable).map(ArticleResponse::from);
        ArticlesResponse articlesResponse = ArticlesResponse.from(articles);
        return ResponseEntity.ok(articlesResponse);
    }

    @PostMapping
    public ResponseEntity<Void> saveArticle(@RequestBody ArticleRequest articleRequest,
                                            @AuthenticationPrincipal MemberDto memberDto) {
        Long articleId = articleService.saveArticle(articleRequest.toDto(memberDto));
        return ResponseEntity.created(URI.create("/api/articles/" + articleId)).build();
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> findArticle(@PathVariable Long articleId) {
        ArticleResponse articleResponse = ArticleResponse.from(articleService.findById(articleId));
        return ResponseEntity.ok(articleResponse);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<Void> updateArticle(@PathVariable Long articleId,
                                              @RequestBody ArticleUpdateRequest articleUpdateRequest,
                                              @AuthenticationPrincipal MemberDto memberDto) {
        articleService.updateArticle(articleId, articleUpdateRequest.toDto(memberDto));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long articleId, @AuthenticationPrincipal MemberDto memberDto) {
        articleService.deleteArticle(articleId, memberDto);
        return ResponseEntity.noContent().build();
    }

}
