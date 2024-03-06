package com.example.Restfulapiboard.controller;

import com.example.Restfulapiboard.domain.Article;
import com.example.Restfulapiboard.domain.Member;
import com.example.Restfulapiboard.dto.ArticleDto;
import com.example.Restfulapiboard.dto.MemberDto;
import com.example.Restfulapiboard.dto.request.ArticleRequest;
import com.example.Restfulapiboard.dto.response.ArticleResponse;
import com.example.Restfulapiboard.repository.MemberRepository;
import com.example.Restfulapiboard.service.ArticleService;
import com.example.Restfulapiboard.service.MemberService;
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
    private final MemberService memberService;

    @GetMapping
    public ResponseEntity<Page<ArticleResponse>> getAllArticles(@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ArticleResponse> allArticles = articleService.findAll(pageable).map(ArticleResponse::from);
        return ResponseEntity.ok(allArticles);
    }

    @PostMapping
    public ResponseEntity<Void> createArticle(@RequestBody ArticleRequest articleRequest,
                                              @AuthenticationPrincipal MemberDto memberDto) {
        Long articleId = articleService.save(articleRequest.toDto(memberDto));
        return ResponseEntity.created(URI.create("/api/articles/" + articleId)).build();
    }


    @GetMapping("/{articleId}")
    public ResponseEntity<ArticleResponse> getArticle(@PathVariable Long articleId) {
        ArticleResponse articleResponse = ArticleResponse.from(articleService.findById(articleId));
        return ResponseEntity.ok(articleResponse);
    }

}
