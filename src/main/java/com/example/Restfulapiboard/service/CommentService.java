package com.example.Restfulapiboard.service;

import com.example.Restfulapiboard.domain.Article;
import com.example.Restfulapiboard.domain.Comment;
import com.example.Restfulapiboard.domain.Member;
import com.example.Restfulapiboard.dto.CommentDto;
import com.example.Restfulapiboard.dto.MemberDto;
import com.example.Restfulapiboard.exception.AuthorizationException;
import com.example.Restfulapiboard.exception.CommentNotFoundException;
import com.example.Restfulapiboard.repository.ArticleRepository;
import com.example.Restfulapiboard.repository.CommentRepository;
import com.example.Restfulapiboard.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;


    public List<CommentDto> findAll() {
        return commentRepository.findAll().stream().map(CommentDto::from).toList();
    }

    public List<CommentDto> findByArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId).stream().map(CommentDto::from).toList();
    }

    public Integer findCountArticleId(Long articleId) {
        return commentRepository.findByArticleId(articleId).size();
    }

    @Transactional
    public Long saveComment(Long articleId, CommentDto commentDto, MemberDto memberDto) {
        Member member = memberRepository.findById(memberDto.id()).orElseThrow(IllegalArgumentException::new);
        Article article = articleRepository.findById(articleId).orElseThrow(IllegalArgumentException::new);
        article.addCommentsCount();
        return commentRepository.save(commentDto.toEntity(member, article)).getId();
    }

    @Transactional
    public void deleteComment(Long commentId, MemberDto memberDto) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);
        validateAuthor(memberDto, comment);
        commentRepository.deleteById(commentId);
    }

    private void validateAuthor(MemberDto memberDto, Comment comment) {
        if (!comment.isAuthor(memberDto.id())) {
            throw new AuthorizationException();
        }
    }
}
