package com.example.Restfulapiboard.service;

import com.example.Restfulapiboard.domain.Article;
import com.example.Restfulapiboard.dto.ArticleDto;
import com.example.Restfulapiboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Page<ArticleDto> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(ArticleDto::from);
    }

    public ArticleDto findById(Long id) {
        return articleRepository.findById(id).map(ArticleDto::from).orElseThrow(IllegalArgumentException::new);
    }

    public void save(ArticleDto articleDto) {
        articleRepository.save(articleDto.toEntity());
    }
}
