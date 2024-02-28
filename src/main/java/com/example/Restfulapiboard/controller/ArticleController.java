package com.example.Restfulapiboard.controller;

import com.example.Restfulapiboard.domain.Article;
import com.example.Restfulapiboard.domain.Member;
import com.example.Restfulapiboard.dto.ArticleDto;
import com.example.Restfulapiboard.dto.request.ArticleRequest;
import com.example.Restfulapiboard.dto.response.ArticleResponse;
import com.example.Restfulapiboard.service.ArticleService;
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

    @GetMapping
    public ResponseEntity<Page<ArticleResponse>> articles(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ArticleResponse> articles = articleService.findAll(pageable).map(ArticleResponse::from);
        return ResponseEntity.ok(articles);
    }

    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody ArticleRequest articleRequest,
                                              @AuthenticationPrincipal Member member) {
        articleService.save();
        return ResponseEntity.created(URI.create("/api/articles/" + articleDto.id())).build();
    }


    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> article(@PathVariable Long articleId) {
        ArticleResponse articleResponse = ArticleResponse.from(articleService.findById(articleId));
        return ResponseEntity.ok(articleResponse);
    }

}
