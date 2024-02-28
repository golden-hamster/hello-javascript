package com.example.Restfulapiboard.repository;

import com.example.Restfulapiboard.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
