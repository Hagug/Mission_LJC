package com.example.myboard.repo;

import com.example.myboard.entity.Article;
import com.example.myboard.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByArticleId(Long articleId);
}
