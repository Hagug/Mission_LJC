package com.example.myboard;

import com.example.myboard.entity.Article;
import com.example.myboard.entity.Board;
import com.example.myboard.entity.Comment;
import com.example.myboard.repo.ArticleRepository;
import com.example.myboard.repo.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // 게시글 반환
    public Article getArticle(Long id) {
        Optional<Article> article = articleRepository.findById(id);
        return article.orElse(null);
    }

    // 게시글 수정
    public void updateArticle(Long id, String topic, String title, String content) {
        Article article = getArticle(id);
        article.setTopic(topic);
        article.setTitle(title);
        article.setContent(content);
        articleRepository.save(article);
    }

    // 게시글 삭제
    public void deleteArticle(Long id) {
        articleRepository.deleteById(id);
    }

    // 댓글 리스트 반환
    public List<Comment> getCommentList(Long articleId) {
        List<Comment> comments = commentRepository.findAllByArticleId(articleId);
        return comments;
    }

    // 댓글 작성
    public void writeComment(Long articleId, String content, String password) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setPassword(password);
        comment.setArticle(articleRepository.findById(articleId).orElse(null));
        commentRepository.save(comment);
    }

    // 댓글 삭제
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    // 댓글 반환
    public Comment getComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElse(null);
    }
}
