package com.example.myboard.repo;

import com.example.myboard.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTopic(String topic);
}
