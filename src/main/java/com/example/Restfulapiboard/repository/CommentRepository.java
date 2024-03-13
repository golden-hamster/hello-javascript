package com.example.Restfulapiboard.repository;

import com.example.Restfulapiboard.domain.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @EntityGraph(attributePaths = {"member", "article"})
    List<Comment> findByArticleId(Long article_id);
}
