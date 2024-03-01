package com.example.Restfulapiboard.service;

import com.example.Restfulapiboard.domain.Article;
import com.example.Restfulapiboard.domain.Member;
import com.example.Restfulapiboard.dto.ArticleDto;
import com.example.Restfulapiboard.repository.ArticleRepository;
import com.example.Restfulapiboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    public Page<ArticleDto> findAll(Pageable pageable) {
        return articleRepository.findAll(pageable).map(ArticleDto::from);
    }

    public ArticleDto findById(Long id) {
        return articleRepository.findById(id).map(ArticleDto::from).orElseThrow(IllegalArgumentException::new);
    }

    @Transactional
    public Long save(ArticleDto articleDto) {
        Member member = memberRepository.findByUsername(articleDto.memberDto().username()).orElseThrow(IllegalArgumentException::new);
        return articleRepository.save(articleDto.toEntity(member)).getId();
    }
}
