package com.example.Restfulapiboard.service;

import com.example.Restfulapiboard.dto.CommentDto;
import com.example.Restfulapiboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public List<CommentDto> findAll() {
        return commentRepository.findAll().stream().map(CommentDto::from).toList();
    }

    public List<CommentDto> findComments(Long articleId) {
         return commentRepository.findByArticleId(articleId).stream().map(CommentDto::from).toList();
    }
}
