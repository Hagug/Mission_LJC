package com.example.myboard;

import com.example.myboard.entity.Board;
import com.example.myboard.entity.Article;
import com.example.myboard.repo.ArticleRepository;
import com.example.myboard.repo.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final ArticleRepository articleRepository;

    // 게시판 생성
    public List<Board> create() {
        if(boardRepository.count() == 0) {
            String[] topics = new String[] {"자유", "개발", "일상", "사건사고", "전체"};
            for(String topic : topics) {
                Board board = new Board();
                board.setTopic(topic);
                boardRepository.save(board);
            }
        }
        return boardRepository.findAll();
    }

    // 게시판의 게시글 리스트 반환
    public List<Article> getArticleList(String topic) {
        List<Article> articleList;
        // 전체 게시판은 모든 게시글의 리스트 반환
        if(topic.equals("전체")) {
            articleList = articleRepository.findAll();
        }
        // 게시판의 주제와 게시글의 주제가 일치하는 게시글 리스트 반환
        else {
            articleList = articleRepository.findByTopic(topic);
        }
        return articleList;
    }

    // 게시판 반환
    public Board getBoard(String topic) {
        Board board = boardRepository.findByTopic(topic);
        return board;
    }

    // 게시글 작성
    public void write(String topic, String title, String content, String password) {
        Article article = new Article();
        article.setTopic(topic);
        article.setTitle(title);
        article.setContent(content);
        article.setPassword(password);
        articleRepository.save(article);
    }
}
